package com.example.spring.integration.controller;

import com.example.spring.RestControllers.RestRegionController;
import com.example.spring.dto.AddRegionDto;
import com.example.spring.dto.EditRegionDto;
import com.example.spring.dto.RegionInfoDto;
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

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
        List<RegionInfoDto> dtos = Models.allRegionsIntegration();
        JSONParser jsonParser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
        ObjectMapper om = new ObjectMapper();
        String resp = om.writeValueAsString(dtos);
        JSONArray jsonArray = (JSONArray) jsonParser.parse(resp);
        this.mockMvc.perform(get(regionLink + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").value(jsonArray));
    }

    @Test
    public void getRegionById() throws Exception {
        List<RegionInfoDto> dtos = Models.allRegionsIntegration();
        this.mockMvc.perform(get(regionLink + "/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(dtos.get(2).getName()));
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
