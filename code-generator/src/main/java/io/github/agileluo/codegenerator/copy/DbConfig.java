package io.github.agileluo.codegenerator.copy;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.io.FileUtils;

import io.github.agileluo.codegenerator.generate.Conf;
import io.github.agileluo.codegenerator.util.CodeUtil;

/**
 * Db配置<br/>
 * 1, 部署table <br/>
 * 2, 配置菜单 <br/>
 * 
 * @author marlon.luo
 *
 */
public class DbConfig {

	private Class<?> c;
	private Conf config;

	public DbConfig(Class<?> c, Conf config) {
		this.c = c;
		this.config = config;
	}
	public void config(){
		if(config.isAllow(Conf.CONFIG_MENU)){
			configMenu();
		}
		if(config.isAllow(Conf.DEPLOY_TABLE)){
			deployTable();
		}
	}
	
	public void configMenu() {
		try {
			
			
			String menuUrl = "/static/html/" + CodeUtil.getAggregateModel(c) + "/" + CodeUtil.getModelName(c) + "-list.shtml";

			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://" + config.getMenuDbHost() + ":3306/" + config.getMenuDbName();
			String username = config.getMenuDbUser();
			String password = config.getMenuDbPassword();
			Connection conn = null;
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
			Statement stat = conn.createStatement();
			ResultSet result = stat.executeQuery("select * from menu m where m.app_code='" + config.getAppName()
					+ "' and m.`level` = 1 and m.`name`='" + config.getMainMenuName() + "'");
			Integer parentId = null;
			if (result.next()) {
				System.err.println("菜单主菜单 已配置： 【应用=" + config.getAppName() + ", 主菜单=" + config.getMainMenuName() + "】");
				parentId = result.getInt(1);
			} else {
				// 配置主目录
				String sql = "INSERT INTO menu (`app_code`, `level`, `parent_id`, `name`, `url`, `tab_label`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('"
						+ config.getAppName() + "', 1, NULL, '" + config.getMainMenuName() + "', '', '', 'codeGenerate', now(), 'codeGenerate', now())";
				stat.execute(sql);
				System.out.println("菜单主目录 配置成功： 【应用=" + config.getAppName() + ", 主菜单=" + config.getMainMenuName() + "】");
				result = stat.executeQuery("select max(m.id) from menu m");
				if (result.next()) {
					parentId = result.getInt(1);
				}
			}

			// 配置子目录
			result = stat.executeQuery("select * from menu m where m.app_code='" + config.getAppName()
					+ "' and m.`level` = 2 and m.`name`='" + config.getMenuName() + "'");
			if (result.next()) {
				System.err.println("菜单子菜单 已配置： 【应用=" + config.getAppName() + ", 子菜单=" + config.getMenuName() + "】");
			} else {
				String sql = "INSERT INTO menu (`app_code`, `level`, `parent_id`, `name`, `url`, `tab_label`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('"
						+ config.getAppName() + "', 2, " + parentId + ", '" + config.getMenuName() + "', '" + menuUrl + "', '" + config.getMenuName()
						+ "', 'codeGenerate', now(), 'codeGenerate', now())";
				stat.execute(sql);
				System.out.println("菜单子菜单 配置成功： 【应用=" + config.getAppName() + ", 子菜单=" + config.getMenuName() + "】");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void deployTable() {
		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://" + config.getDeployDbHost() +  ":3306/" + config.getDeployDbName();
			String username = config.getDeployDbUser();
			String password = config.getDeployDbPassword();
			Connection conn = null;
			Class.forName(driver); // classLoader,加载对应驱动
			conn = (Connection) DriverManager.getConnection(url, username, password);
			Statement stat = conn.createStatement();
			String tableName = CodeUtil.toDbName(CodeUtil.getModelName(c));
			ResultSet result = stat.executeQuery("select TABLE_NAME from INFORMATION_SCHEMA.TABLES where TABLE_SCHEMA='"
					+ config.getDeployDbName() + "' and TABLE_NAME='" + tableName + "'");
			if (result.next()) {
				System.err.println("表已存在: " + tableName);
			} else {
				String ddl = FileUtils.readFileToString(new File("target/" + CodeUtil.getModelName(c) + ".sql"),
						"UTF-8");
				stat.execute(ddl);
				System.out.println("表部署成功：" + tableName);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
