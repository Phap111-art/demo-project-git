package com.example.projectdemogit.config;

import com.example.projectdemogit.entity.Role;
import com.example.projectdemogit.entity.User;
import com.example.projectdemogit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class InitBeanObject {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Bean
    public Role getRole(){
        return Role.builder().roleId(1).build();
    }
    @Bean
    public User getUser(){
        userRepository.deleteAll();

        User u = User.builder().username("tuan").password(passwordEncoder.encode("123")).isActive(true).email("tuan@123").roles(Set.of(getRole())).build();
        return userRepository.save(u);
    }
}
