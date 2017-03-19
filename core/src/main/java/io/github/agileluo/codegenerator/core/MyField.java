package io.github.agileluo.codegenerator.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <b>框架核心-字段</b>
 * <ol>
 * <li>comment: 字段名称</li>
 * <li>key: 数据库主键 及关键日志记录功能 {@linkplain com.yzb.framework.core.annotation.SparkAutoLog SparkAutoLog}</li>
 * <li>autoIncrease：主键是否自增长 </li>
 * <li>length: ddl数据库长度、前端输入框长度校验及后台自动校验（后台未实现）</li>
 * <li>codeType： 编码对应的类型代码， 数据由基础模块basic提供，快速生成编码参见 {@linkplain com.yzb.framework.code.commonCode.GenSql GenSql}</li>
 * <li>notNull: 是否为空， 用于ddl及页面校验</li>
 * <li>query: ibatis动态查询字段</li>
 * <li>bigText: 对应mysql text类型字段</li>
 * </ol>
 * 
 * 
 * @author marlon.luo
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyField {
	/**
	 * 注释
	 * @return
	 */
	String comment();
	/**
	 * 是否为主键
	 * 
	 * @return
	 */
	boolean key() default false;
	/**
	 * 主键是否自增长
	 * 
	 * @return
	 */
	boolean autoIncrease() default false;
	/**
	 * 字段长度
	 * 
	 * @return
	 */
	int length() default 0;

	/**
	 * 类型代码
	 * 
	 * @return
	 */
	String codeType() default "";
	/**
	 * 是否不为空
	 * 
	 * @return
	 */
	boolean notNull() default false;

	/**
	 * 是否为ibatis动态查询条件
	 * 
	 * @return
	 */
	boolean query() default false;

	/**
	 * 是否为索引
	 * 
	 * @return
	 */
	boolean index() default false;

	/**
	 * 是否为唯一索引
	 * 
	 * @return
	 */
	boolean uniqueKey() default false;
	/**
	 * 是否为大字段， 是则对应mysql text类型
	 * 
	 * @return
	 */
	boolean bigText() default false;

	/**
	 * ibatis 动态查询sql排序字段
	 * 
	 * @return
	 */
	boolean orderBy() default false;

}
