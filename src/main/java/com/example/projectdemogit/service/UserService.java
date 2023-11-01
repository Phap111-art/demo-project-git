package com.example.projectdemogit.service;

import com.example.projectdemogit.dtos.request.user.CreateUserDto;
import com.example.projectdemogit.dtos.request.user.LoginUserDto;
import com.example.projectdemogit.dtos.response.CustomResponse;
import com.example.projectdemogit.entity.User;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface UserService {
    CustomResponse createUser(CreateUserDto dto , BindingResult result);
    CustomResponse updateUser(String id,CreateUserDto dto , BindingResult result);
    CustomResponse loginUser(LoginUserDto dto , BindingResult result);
    CustomResponse findByIdUser(String id);
    CustomResponse deleteByIdUser(String id);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);

}
