package com.rakesh.spring_redis.repo;

import com.rakesh.spring_redis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
