package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author pengsong
 */
@Configuration
public class RabbitMqConfig {


    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }
}
