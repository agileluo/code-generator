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
	private String pack;

	private String basePath;
	private String staticPath;

	private Conf config;

	public CopyFile(Class<?> c, Conf config) {
		this.config = config;

		this.className = c.getSimpleName();
		this.model = className.substring(0, 1).toLowerCase() + className.substring(1);
		String packageName = c.getPackage().getName();
		this.pack = packageName.substring(8, packageName.lastIndexOf(".")).replaceAll("\\.", "/");
		this.javaBase = javaSourceBase + packageName.substring(0, packageName.lastIndexOf(".")).replaceAll("\\.", "/");

		basePath = "./";
		staticPath = "/app/yzb-web-static/trunk/static/html" ;
		
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

		if (config.isAllow(Conf.DAO)) {
			mkdir(basePath + "/" + javaBase + "/dao/impl");
		}
		if (config.isAllow(Conf.SERVICE)) {
			mkdir(basePath + "/" + javaBase + "/service/impl");
		}
		if (config.isAllow(Conf.REST)) {
			mkdir(basePath + "/" + javaBase + "/web");
		}
		if (config.isAllow(Conf.STATIC)) {
			mkdir(staticPath + pack);
		}
	}

	private void mkdir(String file) throws IOException {
		if (!(new File(file).exists()))
			FileUtils.forceMkdir(new File(file));
	}

	private String sourceDir = "target/";

	public void copySource() throws IOException {
		if (config.isAllow(Conf.IBATIS)) {
			copyFile(model + "-ibatis.xml", basePath + "/src/main/resources/ibatis");
		}
		if (config.isAllow(Conf.SERVICE)) {
			copyFile(className + "Service.java", javaBase + "/service");
			copyFile(className + "ServiceImpl.java", basePath + "/" + javaBase + "/service/impl");
		}
		if (config.isAllow(Conf.DAO)) {
			copyFile(className + "Dao.java", basePath + "/" + javaBase + "/dao");
			copyFile(className + "DaoImpl.java", basePath + "/" + javaBase + "/dao/impl");
		}
		if (config.isAllow(Conf.REST)) {
			copyFile(className + "Controller.java", basePath + "/" + javaBase + "/web");
		}
		if (config.isAllow(Conf.STATIC)) {
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
