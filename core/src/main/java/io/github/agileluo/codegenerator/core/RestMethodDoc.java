package io.github.agileluo.codegenerator.core;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * rest接口方法文档
 * @author marlon.luo
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RestMethodDoc {
	/**
	 * 名称
	 * @return
	 */
	String name();
	/**
	 * 描述
	 * @return
	 */
	String desc();
	/**
	 * 操作类型
	 * @return
	 */
	String type() default "";
	/**
	 * 异常信息
	 * @return
	 */
	Class<?>[] errs() default {};
}
