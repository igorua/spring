package com.example.spring.controller;

import com.example.spring.Controller.ExceptionHandlerController;
import com.example.spring.RestControllers.RestRegionController;
import com.example.spring.Service.RegionService;
import com.example.spring.dto.AddRegionDto;
import com.example.spring.dto.EditRegionDto;
import com.example.spring.exception.RegionDoesNotExistException;
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
public class RegionControllerTest {
    @InjectMocks
    RestRegionController regionController;
    @Mock
    RegionService regionService;

    private MockMvc mockMvc;
    private String regionLink = "/rest/region";

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(regionController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
    }

    @Test
    void createRegion() throws Exception {
        AddRegionDto dto = Models.getAddRegionDto();
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);

        mockMvc.perform(post(regionLink)
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllLocations() throws Exception {
        mockMvc.perform(get(regionLink + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getLocationById() throws Exception {
        mockMvc.perform(get(regionLink + "/1")
        ).andExpect(status().isOk());
    }

    @Test
    void getLocationByIdHandleException() throws Exception {
        when(regionService.getRegionById(1L)).thenThrow(RegionDoesNotExistException.class);

        mockMvc.perform(get(regionLink + "/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateRegion() throws Exception {
        EditRegionDto dto = Models.getEditRegionDto();
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);

        mockMvc.perform(patch(regionLink + "/1")
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateRegionHandleException() throws Exception {
        EditRegionDto dto = Models.getEditRegionDto();
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);

        doThrow(RegionDoesNotExistException.class).when(regionService).editRegion(1L, dto);

        mockMvc.perform(patch(regionLink + "/1")
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteRegionTest() throws Exception {
        mockMvc.perform(delete(regionLink + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRegionHandleExceptionTest() throws Exception {
        doThrow(RegionDoesNotExistException.class).when(regionService).deleteRegion(1L);

        mockMvc.perform(delete(regionLink + "/1"))
                .andExpect(status().isNotFound());
    }
}
