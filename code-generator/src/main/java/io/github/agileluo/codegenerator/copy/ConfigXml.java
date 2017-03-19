package io.github.agileluo.codegenerator.copy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import io.github.agileluo.codegenerator.generate.Conf;
import io.github.agileluo.codegenerator.util.CodeUtil;

/**
 * 配置相应xml<br/>
 * ibatis配置到sqlMapConfig.xml，service注册, service订阅配置
 * 
 * @author marlon.luo
 *
 */
public class ConfigXml {

	private Class<?> c;
	private Conf config;

	public ConfigXml(Class<?> c, Conf config) {
		this.c = c;
		this.config = config;
	}

	public void gen() {
		if (config.isAllow(Conf.IBATIS)) {
			configIbatis();
		}
		if (config.isAllow(Conf.SERVICE)) {
			registerService();
		}
		if (config.isAllow(Conf.REST)) {
			subscribeService();
		}
	}

	public void configIbatis() {
		try {
			File file = new File(CodeUtil.getProjectRootPath() + "/service/yzb-service-" + config.getServiceProject()
					+ "/trunk/src/main/resources/META-INF/sqlMapConfig.xml");
			List<String> lines = FileUtils.readLines(file, "UTF-8");
			String ibatisFile = CodeUtil.getModelName(c) + "-ibatis.xml";
			boolean isExists = false;
			for (String line : lines) {
				if (line.contains(ibatisFile)) {
					isExists = true;
					break;
				}
			}
			;
			if (isExists) {
				System.err.println("ibatis 已配置： " + ibatisFile);
			} else {
				lines.add(lines.size() - 2, "<sqlMap resource=\"ibatis/" + ibatisFile + "\" /> ");
				FileUtils.writeLines(file, "UTF-8", lines, "\r\n");
				System.out.println("ibatis 配置成功： " + ibatisFile);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void subscribeService() {
		try {
			File file = new File(CodeUtil.getProjectRootPath() + "/app/yzb-web-" + config.getWebProject()
					+ "/trunk/src/main/resources/dubbo-consumer.xml");
			List<String> lines = FileUtils.readLines(file, "UTF-8");
			boolean isExistsService = false;
			String serviceName = CodeUtil.getModelName(c) + "Service";

			for (String line : lines) {

				if (line.contains("id=\"" + serviceName + "\"")) {
					isExistsService = true;
					break;
				}
			}
			;
			if (isExistsService) {
				System.err.println("dubbo service subscribe 已配置： " + serviceName);
			} else {
				System.out.println("dubbo service subscribe 配置成功： " + serviceName);
				lines.add(lines.size() - 2, "	<dubbo:reference id=\"" + serviceName + "\" interface=\""
						+ CodeUtil.getServiceFullName(c) + "\"  />");
				FileUtils.writeLines(file, "UTF-8", lines, "\r\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void registerService() {
		try {
			File file = new File(CodeUtil.getProjectRootPath() + "/service/yzb-service-" + config.getServiceProject()
					+ "/trunk/src/main/resources/META-INF/spring/dubbo-provider.xml");
			List<String> lines = FileUtils.readLines(file, "UTF-8");
			boolean isExistsService = false;
			String serviceName = CodeUtil.getModelName(c) + "Service";

			for (String line : lines) {
				if (line.contains("ref=\"" + serviceName + "\"")) {
					isExistsService = true;
					break;
				}
			}
			;
			if (isExistsService) {
				System.err.println("dubbo service register 已配置： " + serviceName);
			} else {
				System.out.println("dubbo service register 配置成功： " + serviceName);
				lines.add(lines.size() - 2, "	<dubbo:service ref=\"" + serviceName + "\" interface=\""
						+ CodeUtil.getServiceFullName(c) + "\"  />");
				FileUtils.writeLines(file, "UTF-8", lines, "\r\n");
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
