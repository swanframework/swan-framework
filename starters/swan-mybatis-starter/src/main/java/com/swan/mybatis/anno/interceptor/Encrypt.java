package com.swan.mybatis.anno.interceptor;


import com.swan.mybatis.enums.IdGeneratorType;
import com.swan.mybatis.field.id.IdGenerator;
import com.swan.mybatis.field.id.SnowIdGenerator;

import java.lang.annotation.*;

/** 字段加密
 * @author zongf
 * @date 2020-11-27
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Encrypt {

    /** 主键生成器 */
    IdGeneratorType generatorType() default IdGeneratorType.AUTO_INC;

    /** 自定义主键生成器时, 生成器 */
    Class<? extends IdGenerator> generator() default SnowIdGenerator.class;

}