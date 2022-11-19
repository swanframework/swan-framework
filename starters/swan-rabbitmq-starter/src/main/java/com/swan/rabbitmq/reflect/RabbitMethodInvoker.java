package com.swan.rabbitmq.reflect;

import com.alibaba.fastjson.JSONObject;
import com.swan.core.exception.SwanBaseException;
import com.swan.core.invoker.IMethodInvoker;
import com.swan.core.utils.SnowIdUtil;
import com.swan.rabbitmq.anno.RabbitParam;
import com.swan.rabbitmq.anno.RabbitPush;
import com.swan.rabbitmq.beans.RabbitMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.LinkedHashMap;
import java.util.Map;

/** Rabbit 推送
 * @author zongf
 * @since 2020-11-19
 */
@Slf4j
public class RabbitMethodInvoker implements IMethodInvoker {

    private RabbitTemplate rabbitTemplate;

    // spring.rabbitmq.publisher-confirm-type 配置
    private String confirmType;

    public RabbitMethodInvoker(RabbitTemplate rabbitTemplate, String confirmType) {
        this.rabbitTemplate = rabbitTemplate;
        this.confirmType = confirmType;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RabbitMessage messageBaseEntity = null;
        try {

            // 获取注解信息
            RabbitPush rabbitPush = method.getAnnotation(RabbitPush.class);

            // 构造消息体
            messageBaseEntity = new RabbitMessage<>();
            messageBaseEntity.setMessageId(String.valueOf(SnowIdUtil.generateId()));
            messageBaseEntity.setCreateTime(System.currentTimeMillis());
            messageBaseEntity.setUpdateTime(System.currentTimeMillis());
            messageBaseEntity.setBody(this.parseParams(method, args));

            log.info("消息推送, 交换器名称:{}, 路由键:{}, 消息体:{}, 开始", rabbitPush.exchange(), rabbitPush.routingKey(), JSONObject.toJSONString(messageBaseEntity));

            if (rabbitPush.sync()) {

                // 如果spring.rabbitmq.publisher-confirm-type 配置为none, 则抛出异常. none 不支持同步模式
                if (CachingConnectionFactory.ConfirmType.NONE.toString().equals(confirmType.toUpperCase())) {
                    throw new SwanBaseException("spring.rabbitmq.publisher-confirm-type 配置不能为 none");
                }

                // 同步推送消息
                final RabbitMessage msb = messageBaseEntity;
                return rabbitTemplate.invoke(operationCallBack -> {
                    rabbitTemplate.convertAndSend(rabbitPush.exchange(), rabbitPush.routingKey(), msb);
                    return rabbitTemplate.waitForConfirms(rabbitPush.syncTimeOut());
                });
            } else {
                log.info("开启异步消息推送...");
                // 异步推送消息
                rabbitTemplate.convertAndSend(rabbitPush.exchange(), rabbitPush.routingKey(), messageBaseEntity);
            }
        } catch (SwanBaseException ex) {
            throw ex;
        } catch (Exception ex) {
            log.error("消息推送, 请求参数:{}, 失败", JSONObject.toJSONString(messageBaseEntity), ex);
        }

        return null;
    }

    private Object parseParams(Method method, Object[] args){
        // 解析参数
        int paramSize = method.getParameters().length;
        if (paramSize == 0) {
            log.warn("@RabbitPush 消息推送注解用法错误, 方法无参数, 方法签名: {}", method.toString());
            throw new RuntimeException("用法错误!");
        } else if (paramSize == 1) {
            return args[0];
        } else {
            // 解析消息体参数
            Map<String, Object> paramMap = new LinkedHashMap<>(args.length);
            for (int i = 0; i < method.getParameters().length; i++) {
                Parameter parameter = method.getParameters()[i];
                if (parameter.isAnnotationPresent(RabbitParam.class)) {
                    paramMap.put(parameter.getAnnotation(RabbitParam.class).value(), args[i]);
                }else {
                    log.warn("@RabbitPush 消息推送注解用法错误, 方法无参数, 方法签名: {}", method.toString());
                    throw new RuntimeException("用法错误!");
                }
            }
            return paramMap;
        }
    }

}
