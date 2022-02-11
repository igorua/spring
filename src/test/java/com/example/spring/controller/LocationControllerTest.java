package com.example.spring.controller;

import com.example.spring.Controller.ExceptionHandlerController;
import com.example.spring.RestControllers.RestLocationController;
import com.example.spring.Service.LocationService;
import com.example.spring.dto.AddLocationDto;
import com.example.spring.dto.EditLocationDto;
import com.example.spring.exception.LocationDoesNotExistException;
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
class LocationControllerTest {
    @InjectMocks
    RestLocationController locationController;
    @Mock
    LocationService locationService;

    private MockMvc mockMvc;
    private String locationLink = "/rest/location";

    @BeforeEach
    void init() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(locationController)
                .setControllerAdvice(new ExceptionHandlerController())
                .build();
    }

    @Test
    void createLocationTest() throws Exception {
        AddLocationDto dto = Models.createAddLocationDto();
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);

        mockMvc.perform(post(locationLink)
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getAllLocations() throws Exception{
        mockMvc.perform(get(locationLink + "/all"))
                .andExpect(status().isOk());
    }

    @Test
    void getLocationById() throws Exception{
        mockMvc.perform(get(locationLink + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getLocationByIdHandleException() throws Exception {
        when(locationService.getLocationById(1L)).thenThrow(LocationDoesNotExistException.class);

        mockMvc.perform(get(locationLink + "/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateLocation() throws Exception{
        EditLocationDto dto = Models.editLocationDto();
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);
        mockMvc.perform(patch(locationLink + "/1")
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updateLocationHandleException() throws Exception{
        EditLocationDto dto = Models.editLocationDto();
        doThrow(LocationDoesNotExistException.class).when(locationService).updateLocation(1L,dto);
        ObjectMapper om = new ObjectMapper();
        String req = om.writeValueAsString(dto);
        mockMvc.perform(patch(locationLink + "/1")
                        .content(req)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteLocation() throws Exception{
        mockMvc.perform(delete(locationLink + "/1"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteLocationHandleException() throws Exception{
        doThrow(LocationDoesNotExistException.class).when(locationService).deleteLocationByID(1L);
        mockMvc.perform(delete(locationLink + "/1"))
                .andExpect(status().isNotFound());
    }
}
