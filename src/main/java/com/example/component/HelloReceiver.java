package com.example.component;

import com.example.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * @author pengsong
 */
@Component
//@RabbitListener(queues = "hello", containerFactory = "rabbitListenerContainerFactory")
public class HelloReceiver implements BeanPostProcessor, InitializingBean,
        BeanNameAware, BeanFactoryAware, DisposableBean {

    @RabbitListener(queues = "hello")
    public void process(@Payload User user) {
        System.out.println("Receive:" + user.toString());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void setBeanName(String name) {

    }

    @Override
    public void destroy() throws Exception {
        
    }
}
