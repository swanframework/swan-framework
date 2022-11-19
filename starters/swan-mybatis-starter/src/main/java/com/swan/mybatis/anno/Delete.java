package com.swan.mybatis.anno;


import java.lang.annotation.*;

/** 删除字段标识, 不会自动回写到实体中
 * @author zongf
 * @since 2020-11-27
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Delete {

    /** 标识删除成功赋值 */
    String yes() default "1";

    /** 标识删除失败赋值 */
    String no() default "0";

}