package com.example.spring.integration.controller;

import com.example.spring.RestControllers.RestLocationController;
import com.example.spring.dto.AddLocationDto;
import com.example.spring.dto.EditLocationDto;
import com.example.spring.dto.LocationInfoDto;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = "/sqls/LocationSql/insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sqls/LocationSql/delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class LocationControllerTest {
    private final String locationLink = "/rest/location";
    @Autowired
    private RestLocationController locationController;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createLocation() throws Exception {
        AddLocationDto dto = Models.createAddLocationDto();
        ObjectMapper om = new ObjectMapper();
        String requst = om.writeValueAsString(dto);

        this.mockMvc.perform(post(locationLink)
                        .content(requst)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllLocations() throws Exception {
        List<LocationInfoDto> dtos = Models.allLocationDtoIntegration();
        JSONParser jsonParser= new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
        ObjectMapper om = new ObjectMapper();
        String resp = om.writeValueAsString(dtos);
        JSONArray listJson = (JSONArray) jsonParser.parse(resp);

        this.mockMvc.perform(get(locationLink + "/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").value(listJson));
    }

    @Test
    public void getLocationById() throws Exception {
        List<LocationInfoDto> dtos = Models.allLocationDtoIntegration();
        this.mockMvc.perform(get(locationLink + "/9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(dtos.get(8).getId()))
                .andExpect(jsonPath("$.name").value(dtos.get(8).getName()))
                .andExpect(jsonPath("$.longitude").value(dtos.get(8).getLongitude()))
                .andExpect(jsonPath("$.latitude").value(dtos.get(8).getLatitude()));
    }

    @Test
    public void editLocationById() throws Exception {
        EditLocationDto dto = Models.editLocationDto();
        ObjectMapper om = new ObjectMapper();
        String request = om.writeValueAsString(dto);

        this.mockMvc.perform(patch(locationLink + "/1")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteLocationById() throws Exception {
        this.mockMvc.perform(delete(locationLink + "/5"))
                .andExpect(status().isOk());
    }
}
