package com.swan.locker.anno;

import java.lang.annotation.*;

/** 方法参数级别限流
 * @author zongf
 * @since 2022-06-16
 **/
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {

    /** 锁前缀, key=$keyPrefix:$methodName:$keyExpr */
    String keyPrefix() default "locker";

    /** key 业务表达式, 支持 spel 表达式，上下文为参数列表，示例: #paramName.fieldName */
    String keyExpr() default "";

    /** 锁定时间, 单位为毫秒。 支持 spel 表达式，上下文为 spring 容器中的 bean，如: #{beanName.fieldName}  */
    int time() default 1000;

    /** 默认重试次数 */
    int retryTimes() default 0;

    /** 默认重试间隔 300 毫秒*/
    int retryInterval() default 300;

    /** 加锁失败时抛出的错误码 */
    int failedCode() default -1;

    /** 加锁失败时抛出的异常提示 */
    String failedMsg() default "并发异常";


}
