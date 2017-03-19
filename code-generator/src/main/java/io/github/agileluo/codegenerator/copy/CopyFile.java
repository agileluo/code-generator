package io.github.agileluo.codegenerator.copy;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import io.github.agileluo.codegenerator.generate.Conf;
import io.github.agileluo.codegenerator.util.CodeUtil;

/**
 * 文件复制<br/>
 * 将生成的代码复制到相应的模块
 * @author marlon.luo
 *
 */
public class CopyFile {

	private String javaSourceBase = "src/main/java/";
	private String model = null;
	private String className = null;
	private String javaBase;
	private String htmlBase;
	private String pack;

	private String serviceImplPath;
	private String webPath;
	private String staticPath;

	private Conf config;

	public CopyFile(Class<?> c, Conf config) {
		this.config = config;
		String parentPath = CodeUtil.getProjectRootPath();

		this.className = c.getSimpleName();
		this.model = className.substring(0, 1).toLowerCase() + className.substring(1);
		String packageName = c.getPackage().getName();
		this.pack = packageName.substring(8, packageName.lastIndexOf(".")).replaceAll("\\.", "/");
		this.javaBase = javaSourceBase + packageName.substring(0, packageName.lastIndexOf(".")).replaceAll("\\.", "/");
		this.htmlBase = "src/main/webapp";

		serviceImplPath = parentPath + "/service/yzb-service-" + config.getServiceProject() + "/trunk";
		webPath = parentPath + "/app/yzb-web-" + config.getWebProject() + "/trunk";
		staticPath = parentPath + "/app/yzb-web-static/trunk/static/html" ;
		
	}
	public void copy(){
		try {
			makeDir();
			copySource();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void makeDir() throws IOException {

		if (config.isAllow(Conf.GEN_DAO)) {
			mkdir(serviceImplPath + "/" + javaBase + "/dao");
			mkdir(serviceImplPath + "/" + javaBase + "/dao/impl");
		}
		if (config.isAllow(Conf.GEN_SERVICE)) {
			mkdir(javaBase + "/service");
			mkdir(serviceImplPath + "/" + javaBase + "/service/impl");
		}
		if (config.isAllow(Conf.GEN_ACTION)) {
			mkdir(webPath + "/" + javaBase + "/web");
		}
		if (config.isAllow(Conf.GEN_STATIC)) {
			mkdir(staticPath + pack);
		}
	}

	private void mkdir(String file) throws IOException {
		if (!(new File(file).exists()))
			FileUtils.forceMkdir(new File(file));
	}

	private String sourceDir = "target/";

	public void copySource() throws IOException {
		if (config.isAllow(Conf.GEN_IBATIS)) {
			copyFile(model + "-ibatis.xml", serviceImplPath + "/src/main/resources/ibatis");
		}
		if (config.isAllow(Conf.GEN_SERVICE)) {
			copyFile(className + "Service.java", javaBase + "/service");
			copyFile(className + "ServiceImpl.java", serviceImplPath + "/" + javaBase + "/service/impl");
		}
		if (config.isAllow(Conf.GEN_DAO)) {
			copyFile(className + "Dao.java", serviceImplPath + "/" + javaBase + "/dao");
			copyFile(className + "DaoImpl.java", serviceImplPath + "/" + javaBase + "/dao/impl");
		}
		if (config.isAllow(Conf.GEN_ACTION)) {
			copyFile(className + "Controller.java", webPath + "/" + javaBase + "/web");
		}
		if (config.isAllow(Conf.GEN_STATIC)) {
			String htmlPath = staticPath + "/" +  pack ;
			copyFile(model + "-detail.shtml", htmlPath);
			copyFile(model + "-list.shtml", htmlPath);
			copyFile(model + "-pop.shtml", htmlPath);
			copyFile(model + "-select.shtml", htmlPath);
			copyFile(model +"-list.js", htmlPath);
			copyFile(model + "-service.js", htmlPath);
			copyFile(model +"-detail.js", htmlPath);
		}
	}

	private void copyFile(String source, String to) throws IOException {
		if (new File(sourceDir + source).exists()) {
			String toFile = to + "/" + source;
			if (!new File(toFile).exists()) {
				System.out.println("复制成功:" + toFile);
				FileUtils.copyFileToDirectory(new File(sourceDir + source), new File(to), true);
			} else {
				System.err.println("文件已存在:" + toFile);
			}
		}
	}
}
