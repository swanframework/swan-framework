package com.swan.test.rabbitmq;

import com.swan.rabbitmq.anno.EnableRabbitPusher;
import com.swan.test.rabbitmq.bean.UserPO;
import com.swan.test.rabbitmq.pusher.IOrderPusher;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@EnableRabbitPusher
@SpringBootTest
class IOrderPusherTest {

	@Autowired
	private IOrderPusher orderPusher;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private RabbitTemplate.ConfirmCallback confirmCallback;

	@Test
	void pushHello() throws Exception {
		// 同步模式，需要设置 publisher-confirm-type 为 true
		Boolean isSuccess = this.orderPusher.syncPushHello("zhangsan");
		System.out.println("消息是否发送成功:" + isSuccess);
	}

	@Test
	void pushOrderStatus() {
		this.orderPusher.pushOrderStatus("1001", "paying");
	}

	@Test
	void pushUserPO() {
		this.orderPusher.pushUserPO(new UserPO("1001", "paying"));
	}

	@Test
	void doDefault() {
		this.orderPusher.doDefault("zhangsan");
	}


}
