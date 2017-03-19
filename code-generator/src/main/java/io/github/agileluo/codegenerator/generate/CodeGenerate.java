package io.github.agileluo.codegenerator.generate;

import io.github.agileluo.codegenerator.copy.ConfigXml;
import io.github.agileluo.codegenerator.copy.CopyFile;
import io.github.agileluo.codegenerator.copy.DbConfig;
import io.github.agileluo.codegenerator.parse.JavaClassModelParse;
import io.github.agileluo.codegenerator.parse.Model;
import io.github.agileluo.codegenerator.view.VelocityGenerate;

/**
 * 代码生成工具
 * <p>
 * <b>功能说明</b>
 * <ol>
 * <li>生成ddl, ibatis, dao, daoImpl, service, serviceImpl, action, html, js全套增删改查代码</li>
 * <li>代码生成后，开箱即用，无需任何配置</li>
 * <li>可自动部署dll至数据库,自动配置页面菜单</li>
 * <li>功能自由组合搭配,参见{@link Conf}</li>
 * <li>生成的html页面自带校验：非空、长度等</li>
 * <li>自动注册订阅dubbo服务</li>
 * <li>自动配置ibatis文件到sqlMapConfig.xml</li>
 * <li>代码生成不会覆盖原有文件，可放心生成</li>
 * <li>代码生成完全透明、可自由修改相应代码</li>
 * </ol>
 * <p>
 * <b>依赖</b>
 * <ol>
 * <li>{@linkplain com.yzb.framework.core.annotation.SparkModel SparkModel}: 主要定义模型名称</li>
 * <li>{@linkplain com.yzb.framework.core.annotation.SparkField SparkField}: 定义字段注释、长度、是否为查询条件、对应编码类型等 </li>
 * </ol>
 * 
 * @author marlon.luo
 * @version 1.0.0 
 * @version 1.0.1 增加自由搭配
 * @version 1.0.2 静态文件独立，改用shtml + angular实现
 *
 */
public class CodeGenerate {

	/**
	 * 代码生成
	 * 
	 * @param c
	 *            需要配置model
	 * @param config
	 *            各种配置，随意搭配功能
	 */
	public static void generateCode(Class<?> c, Conf config) {
		if (config.isAllow(Conf.DDL) && config.getDeployDbHost() == null) {
			throw new RuntimeException("部署Table需要配置deployDbInfo");
		}
		if (config.isAllow(Conf.MENU) && (config.getMenuDbHost() == null || config.getAppName() == null
				|| config.getMainMenuName() == null || config.getMenuName() == null)) {
			throw new RuntimeException("部署菜单需要配置 menuDbInfo, appName, mainMenuName, menuName");
		}

		JavaClassModelParse p = new JavaClassModelParse(c);
		Model model = p.getModel();
		VelocityGenerate g = new VelocityGenerate();
		g.generate(model);
		new CopyFile(c, config).copy();
		new ConfigXml(c, config).gen();
		new DbConfig(c, config).config();
	}
}
