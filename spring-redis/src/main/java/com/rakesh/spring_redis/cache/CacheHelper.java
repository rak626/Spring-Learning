package com.rakesh.spring_redis.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakesh.spring_redis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class CacheHelper {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            assert o != null;
            return objectMapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.warn("Unable to get data from Redis");
            return null;
        }
    }

    public void set(String key, Object o, Long ttl){
        try {
            String jsonValue = objectMapper.writeValueAsString(o);
            redisTemplate.opsForValue().set(key, jsonValue, ttl, TimeUnit.SECONDS);
        } catch (Exception e){
            log.warn("Unable to set data in Redis ....");
        }
    }

    public String getUserKey(){
        return "user_cache";
    }
}
