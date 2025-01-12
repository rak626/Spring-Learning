package com.rakesh.spring_redis.apptests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    @DisplayName("Redis connection Test")
    public void testSetUp(){
        redisTemplate.opsForValue().set("email" , "this.email");
        Object email = redisTemplate.opsForValue().get("email");
        assert Objects.equals(email, "this.email");
    }
}
