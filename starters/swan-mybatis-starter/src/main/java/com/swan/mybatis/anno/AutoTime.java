package com.swan.mybatis.anno;

import java.lang.annotation.*;

/** 自动设置当前时间
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