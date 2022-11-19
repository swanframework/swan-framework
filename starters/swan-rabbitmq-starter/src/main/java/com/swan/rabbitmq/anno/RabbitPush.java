package com.swan.rabbitmq.anno;

import java.lang.annotation.*;

/** 声明式Rabbit 消息推送 <br/>
 * @author zongf
 * @since 2020-09-07
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RabbitPush {

    /** 交换器名称 */
    String exchange();

    /** 路由键 */
    String routingKey();

    /** 是否同步获取发送结果 <br/>
     *    1) 默认false, 异步发送. 推送方法返回null  <br/>
     *    2) 设置true, 同步发送, 推送方法返回true/false, 表示消息是否发送成功.
     *       需配合 syncTimeOut 设置获取发送确认超时时间.
     *    3) 需配置: spring.rabbitmq.publisher-confirm-type 为simple 或 correlated
     * */
    boolean sync() default false;

    /** 默认确认超时时间: 单位毫秒, 默认10秒. 配合sync 使用 */
    long syncTimeOut() default 10 * 1000;
}
