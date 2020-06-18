package com.baizhi.um.config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

/**
 * @Author SIMBA1949
 * @Date 2020/6/10 22:48
 */
//@Component
public class MyRedisTemple {
    //@Bean("redisTemplate")
    public RedisTemplate getRedisTemple(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate redisTemplate = new RedisTemplate();
        //指定需要的redisC工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        //key的序列化方式，通过json
        redisTemplate.setKeySerializer(new GenericJackson2JsonRedisSerializer());
        //value序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //
        return redisTemplate;
    }
}
