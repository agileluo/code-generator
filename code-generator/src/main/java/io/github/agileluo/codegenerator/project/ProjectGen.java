package io.github.agileluo.codegenerator.project;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import io.github.agileluo.codegenerator.util.CodeUtil;

/**
 * 项目生成工具
 * <p>
 * 可生成 api、service、web项目
 * @author marlon.luo
 *
 */
public class ProjectGen {
	
	public static void main(String[] args) {
		new ProjectGen().genApi("product").genService("product").genWeb("product");
	}
	
	public ProjectGen genApi(String model) {
		try {
			String rootPath = CodeUtil.getProjectRootPath();
			String project = rootPath + "/api/yzb-api-" + model;
			File dir = new File(project);
			if (dir.exists()) {
				System.err.println("api项目已存在： " + project);
			} else {
				String[] dirs = new String[] { "doc", "trunk", "trunk/src/main/java/com/yzb/" + model,
						"trunk/src/test/java/com/yzb/" + model };
				for (String d : dirs) {
					FileUtils.forceMkdir(new File(project + "/" + d));
				}
				FileUtils.copyDirectory(new File("src/main/resources/project/api"), new File(project + "/trunk"));
				replaceContent(dir, model);
				System.out.println("api项目构建成功： " + project);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	public ProjectGen genService(String model) {
		try {
			String rootPath = CodeUtil.getProjectRootPath();
			String project = rootPath + "/service/yzb-service-" + model;
			File dir = new File(project);
			if (dir.exists()) {
				System.err.println("service项目已存在： " + project);
			} else {
				String[] dirs = new String[] { "doc", "trunk", "trunk/src", "trunk/src/main/java/com/yzb/" + model,
						"trunk/src/test/java/com/yzb/" + model };
				for (String d : dirs) {
					FileUtils.forceMkdir(new File(project + "/" + d));
				}
				FileUtils.copyDirectory(new File("src/main/resources/project/service"),
						new File(project + "/trunk"));
				replaceContent(dir, model);
				System.out.println("service项目构建成功： " + project);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	public ProjectGen genWeb(String model) {
		try {
			String rootPath = CodeUtil.getProjectRootPath();
			String project = rootPath + "/app/yzb-web-" + model;
			File dir = new File(project);
			if (dir.exists()) {
				System.err.println("web项目已存在： " + project);
			} else {
				String[] dirs = new String[] { "doc", "trunk", "trunk/src", "trunk/src/main/java/com/yzb/" + model,
						"trunk/src/test/java/com/yzb/" + model };
				for (String d : dirs) {
					FileUtils.forceMkdir(new File(project + "/" + d));
				}
				FileUtils.copyDirectory(new File("src/main/resources/project/web"),
						new File(project + "/trunk"));
				replaceContent(dir, model);
				System.out.println("web项目构建成功： " + project);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return this;
	}
	public ProjectGen genWebOpenApi(String model) {
		try {
			String rootPath = CodeUtil.getProjectRootPath();
			String project = rootPath + "/openapi/yzb-openapi-" + model;
			File dir = new File(project);
			if (dir.exists()) {
				System.err.println("open-api项目已存在： " + project);
			} else {
				String[] dirs = new String[] { "doc", "trunk", "trunk/src", "trunk/src/main/java/com/yzb/" + model,
						"trunk/src/test/java/com/yzb/" + model };
				for (String d : dirs) {
					FileUtils.forceMkdir(new File(project + "/" + d));
				}
				FileUtils.copyDirectory(new File("src/main/resources/project/webopenapi"),
						new File(project + "/trunk"));
				replaceContent(dir, model);
				System.out.println("open-api项目构建成功： " + project);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return this;
	}

	public void replaceContent(File file, String model) {
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				replaceContent(f, model);
			}
		} else {
			try {
				String data = FileUtils.readFileToString(file, "UTF-8");
				data = data.replaceAll("\\$\\{model\\}", model);
				//生成默认dubbo端口
				String root = CodeUtil.getProjectRootPath();
				int serviceSize = new File(root + "/service").list().length -2;
				data = data.replaceAll("\\$\\{dubbo_port\\}", "2088" + serviceSize);
				data = data.replaceAll("\\$\\{tomcat_port\\}", "908" + serviceSize);
				FileUtils.writeStringToFile(file, data, "UTF-8");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	
}
