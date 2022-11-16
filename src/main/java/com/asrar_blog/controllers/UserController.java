package com.asrar_blog.controllers;

import com.asrar_blog.entities.User;
import com.asrar_blog.payloads.UserDTO;
import com.asrar_blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user){
        UserDTO userDto = userService.createUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int id){
        UserDTO user = userService.getGetUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return new ResponseEntity<>("Success",HttpStatus.OK);
    }
}
