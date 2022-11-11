package com.swan.mybatis.anno.interceptor;

import java.lang.annotation.*;

/** 自动维护时间
 * @author zongf
 * @date 2020-11-27
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoTime {

    /** 插入时强制设置为null */
    boolean onCreate() default false;

    /** 更新时强制设置为null */
    boolean onUpdate() default false;

    /** 删除时强制设置为null */
    boolean onDelete() default false;

}