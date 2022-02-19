package com.example.spring.integration.controller;

import com.example.spring.RestControllers.RestUserController;
import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import com.example.spring.dto.GetUserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONArray;
import net.minidev.json.parser.JSONParser;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        List<GetUserDto> dtos = Models.allUsersIntegration();
        JSONParser jsonParser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
        ObjectMapper om = new ObjectMapper();
        String resp = om.writeValueAsString(dtos);
        JSONArray jsonArray = (JSONArray) jsonParser.parse(resp);

        this.mockMvc.perform(get(userLink + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").value(jsonArray));
    }

    @Test
    public void getUserById() throws Exception {
        List<GetUserDto> dtos = Models.allUsersIntegration();
        this.mockMvc.perform(get(userLink + "/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dtos.get(0).getName()))
                .andExpect(jsonPath("$.surname").value(dtos.get(0).getSurname()))
                .andExpect(jsonPath("$.age").value(dtos.get(0).getAge()))
                .andExpect(jsonPath("$.locationInfoDto").value(dtos.get(0).getLocationInfoDto()));
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
