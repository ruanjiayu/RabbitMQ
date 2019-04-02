package com.jing;

import com.jing.bean.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

	@Autowired
	RabbitTemplate rabbitTemplate;

	@Autowired
	AmqpAdmin amqpAdmin;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createExchange(){
		//创建一个交换机
//		amqpAdmin.declareExchange(new DirectExchange("amqpAdmin.directExchange"));
		//创建队列
//		amqpAdmin.declareQueue(new Queue("amqpAdmin.queue",true));
		//绑定关系
		amqpAdmin.declareBinding(new Binding("amqpAdmin.queue", Binding.DestinationType.QUEUE,"amqpAdmin.directExchange","amqp.haha",null));
	}


	/**
	 * 1.单播(点对点)
	 */
	@Test
	public void direct(){
//		messgae 需要自己构造一个;定义消息体的内容和消息头信息
//		rabbitTemplate.send(exchange,routingKey,message);

//		object默认当成消息体，只需要传入要发送的对象，自动序列化发送给rabbitmq
//		rabbitTemplate.convertAndSend(exchange,routingKey,object);

		Map<String,Object> map = new HashMap<>();
		map.put("msg","这是第一个消息");
		map.put("data", Arrays.asList("helloworld",123,true));
		//对象被默认序列化以后发送出去
		rabbitTemplate.convertAndSend("exchange.direct","rjy.news",map);
	}

	/**
	 * 接受数据
	 */
	@Test
	public void receive(){
		Object o = rabbitTemplate.receiveAndConvert("rjy.news");
		System.out.println(o.getClass());
		System.out.println(o);
	}

	/**
	 * 2.广播模式
	 */
	@Test
	public void sendMsg(){
		rabbitTemplate.convertAndSend("exchange.fanout","",new Book("龙族","阮佳裕"));
	}


}
