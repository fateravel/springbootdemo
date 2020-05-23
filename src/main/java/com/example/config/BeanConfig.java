package com.example.config;

import com.example.interceptor.AuthorizeInterceptor;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author pengsong
 */
@Configuration
public class BeanConfig {

    /**
     * Type safe representation of application.properties
     */
    @Autowired
    ClusterConfigurationProperties clusterProperties;

    /*public @Bean RedisConnectionFactory connectionFactory() {

        return new JedisConnectionFactory(
                new RedisClusterConfiguration(clusterProperties.getNodes()));
    }*/

    @Bean
    public AuthorizeInterceptor authorizeHandlerInterceptor() {
        return new AuthorizeInterceptor();
    }

    @Bean
    public Redisson redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://47.106.149.90:6379").setPassword("redis6379");
        return (Redisson )Redisson.create(config);
    }
}
