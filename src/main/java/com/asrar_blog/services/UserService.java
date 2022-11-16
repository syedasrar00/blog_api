package com.asrar_blog.services;

import com.asrar_blog.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO, Integer userId);

    UserDTO getGetUserById(Integer userId);

    List<UserDTO> getAllUsers();

    void deleteUser(Integer userId);

}
