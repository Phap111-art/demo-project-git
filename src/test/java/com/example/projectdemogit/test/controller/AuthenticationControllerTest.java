package com.example.projectdemogit.test.controller;

import com.example.projectdemogit.controller.AuthenticationController;
import com.example.projectdemogit.dtos.request.user.CreateUserDto;
import com.example.projectdemogit.dtos.response.CustomResponse;
import com.example.projectdemogit.entity.User;
import com.example.projectdemogit.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j

public class AuthenticationControllerTest {

    @MockBean
    private UserService userService;

    private MockMvc mvc;

    private ObjectMapper om;

    private CreateUserDto dto;

    private CustomResponse response;

    @BeforeEach
    public void init() {
        om = new ObjectMapper();
        mvc = MockMvcBuilders.standaloneSetup(new AuthenticationController(userService)).build();
        dto = CreateUserDto.builder().username("tuan").password("1234567").email("tuan@123")
                .isActive(true).roleId(new HashSet<>(Arrays.asList(1, 2, 3))).build();
        response = new CustomResponse("User created successfully!", HttpStatus.CREATED.value(), dto);
    }

    @Test
    public void shouldReturnSuccessWhenSavingUser() throws Exception {
//        when(userService.createUser(eq(dto), any(BindingResult.class))).thenReturn(response);
//        // Act and Assert
//        mvc.perform(post("/auth/create-user")
//                .content(asJsonString(dto))
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.httpStatus").value(response.getHttpStatus()))
//                .andExpect(jsonPath("$.data.username").value(dto.getUsername()));
//        // Verify that the userService.createUser() method was called with the correct arguments
//        verify(userService).createUser(eq(dto), any(BindingResult.class));
    }

    public static String asJsonString(final Object obj) { // chuyển đối object thành json
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
