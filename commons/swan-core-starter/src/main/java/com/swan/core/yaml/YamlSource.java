package com.swan.core.yaml;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface YamlSource {

	/** 资源文件名称 */
	String name() default "";

	/** 资源文件路径 */
	String[] value();

	/** 未找到资源文件时，是否忽略*/
	boolean ignoreResourceNotFound() default false;

	/** 编码格式 */
	String encoding() default "";


}
