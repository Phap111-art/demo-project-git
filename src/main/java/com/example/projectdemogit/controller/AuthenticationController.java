package com.example.projectdemogit.controller;

import com.example.projectdemogit.dtos.request.user.CreateUserDto;
import com.example.projectdemogit.dtos.request.user.LoginUserDto;
import com.example.projectdemogit.dtos.response.CustomResponse;
import com.example.projectdemogit.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    @Autowired
    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/login")
    public ResponseEntity<?> login(){
        return ResponseEntity.ok().build();
    }
    @PostMapping("/login")
    public ResponseEntity<CustomResponse> ValidLogin(@RequestBody @Valid LoginUserDto dto,BindingResult result){
        return ResponseEntity.status(HttpStatus.OK).body(userService.loginUser(dto,result));
    }

    @PostMapping("/create-user")
    public ResponseEntity<CustomResponse> createUser(@RequestBody @Valid CreateUserDto dto, BindingResult result){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(dto,result));
    }
    @PutMapping("/update-user/{id}")
    public ResponseEntity<CustomResponse> updateUser(@PathVariable String id, @RequestBody @Valid CreateUserDto dto, BindingResult result){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,dto,result));
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<CustomResponse> findByIdUser(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByIdUser(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteByIdUser(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteByIdUser(id));
    }
    @GetMapping("/home")
    public ResponseEntity<?> home(){
        return ResponseEntity.status(HttpStatus.OK).body("Login Successfully!");
    }
}
