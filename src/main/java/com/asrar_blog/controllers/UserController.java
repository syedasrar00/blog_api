package com.asrar_blog.controllers;

import com.asrar_blog.payloads.ApiResponse;
import com.asrar_blog.payloads.UserDTO;
import com.asrar_blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getUsers(){
        List list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO user){
        UserDTO userDto = userService.createUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") int id){
        UserDTO user = userService.getGetUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable int id){
        UserDTO user = userService.updateUser(userDTO,id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return new ResponseEntity<>(new ApiResponse("Deleted sucessfully",true),HttpStatus.OK);
    }
}
