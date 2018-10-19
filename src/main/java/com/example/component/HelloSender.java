package com.example.component;

import com.example.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author pengsong
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello:" + new Date();
        User user = new User();
        user.setId(1L);
        user.setUsername("fateravel");
        user.setPassword("123456");
        rabbitTemplate.convertAndSend("hello", user);
    }
}
