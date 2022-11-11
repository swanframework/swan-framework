package com.swan.mybatis.anno;

import java.lang.annotation.*;

/** 乐观锁控制，不会自动回写到实体中
 * @author zongf
 * @date 2020-11-27
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {

    /** 版本号起始值, 默认从1开始 */
    int start() default 1;

}