package com.example.spring.integration.controller;

import com.example.spring.RestControllers.RestUserController;
import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import util.Models;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/sqls/UserSql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sqls/UserSql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class UserControllerTest {
    private final String userLink = "/rest/user";
    @Autowired
    private RestUserController userController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createUser() throws Exception {
        CreateUserDto dto = Models.getCreateUserDto();
        ObjectMapper om = new ObjectMapper();
        String requst = om.writeValueAsString(dto);

        this.mockMvc.perform(post(userLink)
                .content(requst)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllUsers() throws Exception {
        this.mockMvc.perform(get(userLink + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUserById() throws Exception {
        this.mockMvc.perform(get(userLink + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void editUSerById() throws Exception {
        EditUserDto dto = Models.getEditUserDto();
        ObjectMapper om = new ObjectMapper();
        String requst = om.writeValueAsString(dto);

        this.mockMvc.perform(patch(userLink + "/3")
                .content(requst)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteUserById() throws Exception {
        this.mockMvc.perform(delete(userLink + "/3"))
                .andExpect(status().isOk());
    }
}
