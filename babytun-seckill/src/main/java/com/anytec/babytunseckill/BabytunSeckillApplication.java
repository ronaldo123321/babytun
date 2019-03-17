package com.anytec.babytunseckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.anytec.babytunseckill")
@EnableScheduling //启动任务调度功能
public class BabytunSeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabytunSeckillApplication.class, args);
    }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        //设置value的序列化方式为JSOn
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //设置key的序列化方式为String
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
