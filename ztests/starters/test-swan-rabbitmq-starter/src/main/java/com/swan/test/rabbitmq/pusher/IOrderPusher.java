package com.swan.test.rabbitmq.pusher;

import com.swan.rabbitmq.anno.RabbitParam;
import com.swan.rabbitmq.anno.RabbitPush;
import com.swan.rabbitmq.anno.RabbitPusher;
import com.swan.test.rabbitmq.bean.UserPO;

/**
 * @author zongf
 * @date 2020-11-19
 */
@RabbitPusher
public interface IOrderPusher {

    @RabbitPush(exchange = "E_RABBITMQ_STARTER", routingKey = "RK_RABBITMQ_STARTER", sync = true, syncTimeOut = 20*1000)
    public Boolean syncPushHello(String name);

    @RabbitPush(exchange = "RK_RABBITMQ_STARTER", routingKey = "E_RABBITMQ_STARTER")
    public void pushOrderStatus(@RabbitParam("orderId")String orderId, @RabbitParam("status")String status);

    @RabbitPush(exchange = "RK_RABBITMQ_STARTER", routingKey = "E_RABBITMQ_STARTER")
    public void pushUserPO(UserPO userPO);

    default public void doDefault(String name){
        System.out.println("This is default method ! name:" + name);
    }

}
