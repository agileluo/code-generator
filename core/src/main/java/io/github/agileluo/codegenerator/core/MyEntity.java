package io.github.agileluo.codegenerator.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 框架核心模型<br/>
 * <ol>
 * <li>可自动生成全套CRUD代码（service, dao, ibatis, contraller, html, js），页面包含校验（非空、长度等）</li>
 * <li>可自动部署DDL至DB</li>
 * <li>自动配置xml（ibatis, 注册及订阅dubbo服务）</li>
 * </ol>
 * 
 * @author marlon.luo
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyEntity {
	String comment();
	/**是否可以修改*/
	boolean editAble() default true;
}
