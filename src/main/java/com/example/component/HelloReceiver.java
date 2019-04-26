package com.example.component;

import com.example.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author pengsong
 */
//@Component
//@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(User user) {
        System.out.println("Receive:" + user.toString());
    }
}
