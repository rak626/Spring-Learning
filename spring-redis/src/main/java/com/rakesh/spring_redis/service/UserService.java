package com.rakesh.spring_redis.service;

import com.rakesh.spring_redis.cache.CacheHelper;
import com.rakesh.spring_redis.entity.User;
import com.rakesh.spring_redis.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CacheHelper cacheHelper;
    public User addUser(User user) {
        return userRepo.save(user);
    }

    public User getUser(Long id) {
        User user = cacheHelper.get(cacheHelper.getUserKey() + "_" + id, User.class);
        if (user != null) return user;
        User dbUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("unable to find user"));
        cacheHelper.set(cacheHelper.getUserKey() + "_" + id , dbUser, -1L);
        return dbUser;
    }
}
