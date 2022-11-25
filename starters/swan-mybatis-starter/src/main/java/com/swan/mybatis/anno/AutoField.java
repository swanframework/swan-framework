package com.swan.mybatis.anno;

import com.swan.mybatis.mapper.field.IAutoFieldGenerator;

import java.lang.annotation.*;

/** 自动更新字段
 * @author zongf
 * @date 2020-11-27
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoField {

    /** 插入时设置 */
    boolean onCreate() default false;

    /** 更新时设置 */
    boolean onUpdate() default false;

    /** 删除时设置 */
    boolean onDelete() default false;

    /** 提供者 */
    Class<? extends IAutoFieldGenerator> generator() ;

}