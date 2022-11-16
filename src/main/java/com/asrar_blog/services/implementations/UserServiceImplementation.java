package com.asrar_blog.services.implementations;

import com.asrar_blog.entities.User;
import com.asrar_blog.exceptions.ResourceNotFoundException;
import com.asrar_blog.payloads.UserDTO;
import com.asrar_blog.repositories.UserRepo;
import com.asrar_blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userDTOtoUser(userDTO);
        User savedUser = userRepo.save(user);
        return usertoUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserID",userId));
        User userUpdate = userDTOtoUser(userDTO);
        userUpdate.setId(userId);
        UserDTO updatedUserDTO = usertoUserDTO(userRepo.save(userUpdate));
        return updatedUserDTO;
    }

    @Override
    public UserDTO getGetUserById(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserID",userId));
        return usertoUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> list = userRepo.findAll().stream().map(user -> usertoUserDTO(user)).collect(Collectors.toList());
        return list;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserID",userId));
        userRepo.delete(user);
    }
    private User userDTOtoUser(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getEmail());
        user.setAbout(userDTO.getAbout());
        return user;
    }
    private UserDTO usertoUserDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getEmail());
        userDTO.setAbout(user.getAbout());
        return userDTO;
    }
}
