package com.swan.mybatis.anno;

import com.swan.mybatis.mapper.field.encrypt.IEncryptor;
import com.swan.mybatis.mapper.field.encrypt.Md5Encryptor;

import java.lang.annotation.*;

/** 字段加密
 * @author zongf
 * @since 2020-11-27
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Encrypt {

    /** 自定义主键生成器时, 生成器 */
    Class<? extends IEncryptor> encryptor() default Md5Encryptor.class;

    /** 插入时强制设置为null */
    boolean onCreate() default false;

    /** 更新时强制设置为null */
    boolean onUpdate() default false;

    /** 转大写 */
    boolean toUpper() default false;

}