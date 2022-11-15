package com.asrar_blog.controllers;

import com.asrar_blog.entities.User;
import com.asrar_blog.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserRepo userRepo;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userRepo.findAll();
    }
    @PostMapping("/user")
    public User postUser(@RequestBody User user){
        return userRepo.save(user);
    }
}
