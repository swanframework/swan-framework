package com.swan.rabbitmq.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/** Rabbit消息
 * @param <T> 消息体
 * @author zongf
 * @since 2020-11-23
 */
@Setter @Getter
public class RabbitMessage<T> implements Serializable {

    /** 消息id */
    String messageId;

    /** 创建时间 */
    Long createTime;

    /** 消息体 */
    T body;

     /** 第几次入队列, 默认为1 */
    Integer times = 1;

    /** 更新时间 */
    Long updateTime;

}
