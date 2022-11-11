package com.swan.mybatis.anno;

import java.lang.annotation.*;

/** 忽略字段映射
 * @author zongf
 * @date 2020-11-27
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ignore {


}