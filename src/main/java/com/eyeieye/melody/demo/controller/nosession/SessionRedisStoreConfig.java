package com.eyeieye.melody.demo.controller.nosession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class SessionRedisStoreConfig {

    @Bean
    public SessionRedisStore getRedisStore(@Autowired JedisPool jedisPool){
        SessionRedisStore sessionRedisStore = new SessionRedisStore();
        Set<String> stringSet = new HashSet<>();
        stringSet.add("_userext");
        sessionRedisStore.setAttributeNames(stringSet);

        return sessionRedisStore;
    }
}
