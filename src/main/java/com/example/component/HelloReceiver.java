package com.example.component;

import com.example.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author pengsong
 */
@Component
//@RabbitListener(queues = "hello", containerFactory = "rabbitListenerContainerFactory")
public class HelloReceiver {

    @RabbitListener(queues = "hello")
    public void process(@Payload User user) {
        System.out.println("Receive:" + user.toString());
    }
}
