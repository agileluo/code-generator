package io.github.agileluo.codegenerator.generate;

/**
 * 代码生成配置
 * <ol>
 * <li>开启所有功能 enable(Conf.ALLOW_ALL) </li>
 * <li>开启单个功能 enable(Conf.XX) </li>
 * <li>禁用单个功能 disable(Conf.XX) </li>
 * </ol>
 * 
 * 
 * @author marlon.luo
 *
 */
public class Conf {
	/**
	 * 开启所有功能
	 */
	public static final int All = 0b11111111;
	/**
	 * 部署table
	 */
	public static final int DDL = 1 << 0;
	/**
	 * 配置菜单
	 */
	public static final int MENU = 1 << 1;
	/**
	 * 生成ibatis
	 */
	public static final int IBATIS = 1 << 2;
	/**
	 * 生成dao
	 */
	public static final int DAO = 1 << 3;
	/**
	 * 生成service
	 */
	public static final int SERVICE = 1 << 4;

	/**
	 * 生成action
	 */
	public static final int REST = 1 << 5;
	/**
	 * 生成静态文件
	 */
	public static final int STATIC = 1 << 6;

	private int flag;

	private String serviceProject;
	private String webProject;
	
	private String deployDbHost;
	private String deployDbName;
	private String deployDbUser;
	private String deployDbPassword;
	
	private String menuDbHost;
	private String menuDbName;
	private String menuDbUser;
	private String menuDbPassword;
	
	private String appName;
	private String mainMenuName;
	private String menuName;

	/**
	 * 启用功能
	 * @param permission
	 * @return
	 */
	public Conf enable(int permission) {
		flag |= permission;
		return this;
	}
	/**
	 * 禁用功能
	 * @param permission
	 * @return
	 */
	public Conf disable(int permission) {
		flag &= ~permission;
		return this;
	}

	public boolean isAllow(int permission) {
		return (flag & permission) == permission;
	}

	public String getServiceProject() {
		return serviceProject;
	}

	public Conf setServiceProject(String serviceProject) {
		this.serviceProject = serviceProject;
		
		return this;
	}

	public String getWebProject() {
		return webProject;
	}

	public Conf setWebProject(String webProject) {
		this.webProject = webProject;
		this.appName = "yzb-web-" + webProject;
		return this;
	}



	public String getMainMenuName() {
		return mainMenuName;
	}

	public Conf setMainMenuName(String mainMenuName) {
		this.mainMenuName = mainMenuName;
		return this;
	}

	public String getMenuName() {
		return menuName;
	}

	public Conf setMenuName(String menuName) {
		this.menuName = menuName;
		return this;
	}

	public String getAppName() {
		return appName;
	}

	public String getMenuDbName() {
		return menuDbName;
	}

	public String getMenuDbHost() {
		return menuDbHost;
	}

	public String getMenuDbUser() {
		return menuDbUser;
	}

	public String getMenuDbPassword() {
		return menuDbPassword;
	}

	public Conf setMenuDbInfo(String menuDbHost, String menuDbName,  String menuDbUser, String menuDbPassword) {
		this.menuDbName = menuDbName;
		this.menuDbHost = menuDbHost;
		this.menuDbUser = menuDbUser;
		this.menuDbPassword = menuDbPassword;
		return this;
	}

	public Conf setDeployDbInfo(String deployDbHost, String deployDbName, String deployDbUser, String deployDbPassword) {
		this.deployDbHost = deployDbHost;
		this.deployDbName = deployDbName;
		this.deployDbUser = deployDbUser;
		this.deployDbPassword = deployDbPassword;
		return this;
	}

	public String getDeployDbHost() {
		return deployDbHost;
	}

	public String getDeployDbName() {
		return deployDbName;
	}

	public String getDeployDbUser() {
		return deployDbUser;
	}

	public String getDeployDbPassword() {
		return deployDbPassword;
	}
	
	

}
