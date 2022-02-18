package com.example.spring.integration.controller;

import com.example.spring.RestControllers.RestRegionController;
import com.example.spring.dto.AddRegionDto;
import com.example.spring.dto.EditRegionDto;
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

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/sqls/RegionSql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sqls/RegionSql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class RegionControllerTest {
    private final String regionLink = "/rest/region";
    @Autowired
    private RestRegionController regionController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createRegion() throws Exception {
        AddRegionDto dto = Models.getAddRegionDto();
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);

        this.mockMvc.perform(post(regionLink)
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllRegions() throws Exception {
        this.mockMvc.perform(get(regionLink + "/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("{\"id\":1")));
    }

    @Test
    public void getRegionById() throws Exception {
        this.mockMvc.perform(get(regionLink + "/3"))
                .andExpect(status().isOk());
    }

    @Test
    public void editRegionById() throws Exception{
        EditRegionDto dto = Models.getEditRegionDto();
        ObjectMapper om = new ObjectMapper();
        String request = om.writeValueAsString(dto);
        this.mockMvc.perform(patch(regionLink + "/1")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteRegionById() throws Exception{
        this.mockMvc.perform(delete(regionLink + "/1"))
                .andExpect(status().isOk());
    }
}
