package com.jing.service;

import com.jing.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author ruanjiayu
 * @dateTime 2019/4/1 19:02
 */
@Service
public class BookServcie {

    /**
     * 监听rjy.news的消息队列消息，一旦有消息就会接受到
     * @param book
     */
    @RabbitListener(queues = "rjy.news")
    public void receive(Book book){
        System.out.println("收到消息:" + book);
    }

}
