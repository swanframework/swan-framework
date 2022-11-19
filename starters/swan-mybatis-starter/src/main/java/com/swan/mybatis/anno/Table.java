package com.swan.mybatis.anno;


import java.lang.annotation.*;

/** 表定义
 * @author zongf
 * @since 2020-11-27
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    /** 表名称 */
    String name() default "";

}