package com.swan.kafka.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Rabbit消息
 * @param <T> 消息体
 * @author zongf
 * @since 2020-11-23
 */
@Setter
@Getter
public class KafkaMessage<T> implements Serializable {

    /** 消息id */
    Long messageId;

    /** 创建时间 */
    Long createTime;

    /** 消息体 */
    T body;

}
