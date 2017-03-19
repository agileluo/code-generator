## 功能介绍
* 生成ddl, ibatis, dao, daoImpl, service, serviceImpl, action, html, js全套增删改查代码 
* 代码生成后，开箱即用，无需任何配置 
* 可自动部署dll至数据库,自动配置页面菜单 
* 功能自由组合搭配,参见Conf 
* 生成的html页面自带校验：非空、长度等 
* 自动注册订阅dubbo服务 
* 自动配置ibatis文件到sqlMapConfig.xml 
* 代码生成不会覆盖原有文件，可放心生成 
* 代码生成完全透明、可自由修改相应代码

## 如何使用
1，项目中引入code-generator包：

    <groupId>io.github.agileluo.codegenerator</groupId>
	<artifactId>code-generator</artifactId>
	<version>1.0.0</version>

2, 定义实体类，例如

	@MyEntity(comment="用户")
	public class User {
		
		@MyField(comment="用户Id",  key = true, autoIncrease = true)
		private Long userId;
		
		@MyField(comment = "密码", length = 50, notNull = true)
		private String password;
		
		@MyField(comment = "创建人员 ", length=20)
		private String createBy;
	
		@MyField(comment = "创建时间")
		private Date createDate;
	
		@MyField(comment = "更新人员", length=20)
		private String updateBy;
	
		@MyField(comment = "更新时间")
		private Date updateDate;
	
		//TODO  getters and setters
	}

3, 代码生成

    public static void main(String[] args) {
		
		CodeGenerate.generateCode(User.class, new Conf()
				.enable(Conf.All)
				.setServiceProject("service-basic")
				.setWebProject("web-manager")
				.setDeployDbInfo("192.168.208.1", "dandan", "mysql", "12345678")
				.setMenuDbInfo("192.168.208.1", "dandan", "mysql", "12345678")
				.setMainMenuName("运维支持")
				.setMenuName("配置管理"));
	}

