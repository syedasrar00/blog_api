package com.asrar_blog.services.implementations;

import com.asrar_blog.entities.User;
import com.asrar_blog.exceptions.ResourceNotFoundException;
import com.asrar_blog.payloads.UserDTO;
import com.asrar_blog.repositories.UserRepo;
import com.asrar_blog.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;

    public UserServiceImplementation() {
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userDTOtoUser(userDTO);
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        User savedUser = userRepo.save(user);
        return usertoUserDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, Integer userId) {
        userDTO.setId(userId);
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","UserID",userId));
        User userUpdate = userDTOtoUser(userDTO);
        String password = userUpdate.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        userUpdate.setPassword(encodedPassword);
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
        return this.modelMapper.map(userDTO,User.class);
    }
    private UserDTO usertoUserDTO(User user){
        return this.modelMapper.map(user,UserDTO.class);
    }
}
