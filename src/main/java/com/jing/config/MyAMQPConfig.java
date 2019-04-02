package com.jing.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ruanjiayu
 * @dateTime 2019/4/1 18:43
 */

@Configuration
public class MyAMQPConfig {

    /**
     * 配置了消息上传到消息中间件的时候，使用了自己定义的json序列化
     * @return
     */
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
