package com.rakesh.spring_redis.controller;

import com.rakesh.spring_redis.entity.User;
import com.rakesh.spring_redis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user){
        User user1 = userService.addUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }
}
