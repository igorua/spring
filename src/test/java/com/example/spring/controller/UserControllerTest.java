package com.example.spring.controller;

import com.example.spring.Controller.ExceptionHandlerController;
import com.example.spring.RestControllers.RestUserController;
import com.example.spring.Service.UserService;
import com.example.spring.dao.Entity.User;
import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import com.example.spring.exception.UserDoesNotExistException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import util.Models;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@RequiredArgsConstructor
public class UserControllerTest {
    @InjectMocks
    RestUserController userController;
    @Mock
    UserService userService;

    private MockMvc mockMvc;
    private String userLing = "/rest/user";

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
    }

    @Test
    void createUserTest() throws Exception {
        CreateUserDto dto = Models.getCreateUserDto();
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);

        mockMvc.perform(post(userLing)
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllUsersTest() throws Exception {
        mockMvc.perform(get(userLing + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByIdTest() throws Exception {
        mockMvc.perform(get(userLing + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getUserByIdHandleException() throws Exception {
        when(userService.getUserById(1L)).thenThrow(UserDoesNotExistException.class);

        mockMvc.perform(get(userLing + "/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateUser() throws Exception {
        EditUserDto dto = Models.getEditUserDto();
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);

        mockMvc.perform(patch(userLing + "/1")
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateUserHandleException() throws Exception{
        EditUserDto dto = Models.getEditUserDto();
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);

        doThrow(UserDoesNotExistException.class).when(userService).editUser(1L,dto);

        mockMvc.perform(patch(userLing + "/1")
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUserById() throws Exception{
        mockMvc.perform(delete(userLing + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUserByIdHandleException() throws Exception {
        doThrow(UserDoesNotExistException.class).when(userService).deleteUser(1L);
        mockMvc.perform(delete(userLing + "/1"))
                .andExpect(status().isNotFound());
    }

}
