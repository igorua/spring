package com.example.spring.service;

import com.example.spring.ServiceImpl.LocationServiceImpl;
import com.example.spring.dao.Entity.Location;
import com.example.spring.dao.Entity.Region;
import com.example.spring.dao.Repository.LocationRepository;
import com.example.spring.dao.Repository.RegionRepository;
import com.example.spring.dto.AddLocationDto;
import com.example.spring.dto.EditLocationDto;
import com.example.spring.dto.LocationInfoDto;
import com.example.spring.exception.RegionDoesNotExistException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {
    @InjectMocks
    LocationServiceImpl locationService;
    @Mock
    LocationRepository locationRepository;
    @Mock
    RegionRepository regionRepository;

    @Test
    void addLocationService() {
        Location location = Models.createLocation();
        AddLocationDto addLocationDto = Models.createAddLocationDto();

        when(regionRepository.findById(1L)).thenReturn(Optional.of(Models.getRegion()));
        when(locationRepository.save(location)).thenReturn(location);

        locationService.addLocation(addLocationDto);

        verify(regionRepository, times(2)).findById(1L);
        verify(locationRepository).save(location);

    }

    @Test
    void addLocationServiceWithouRegion() {
        Location location = Models.createLocation();
        location.setRegion(Region.builder().name("Name").build());
        AddLocationDto addLocationDto = Models.createAddLocationDto();
        addLocationDto.setRegionId(null);

        when(regionRepository.findById(any())).thenReturn(Optional.empty());
        when(locationRepository.save(location)).thenReturn(location);

        locationService.addLocation(addLocationDto);

        verify(regionRepository, times(1)).findById(any());
        verify(locationRepository).save(location);

    }

    @Test
    void getAllLocations() {
        Location location = Models.createLocation();
        LocationInfoDto dto = Models.getLocationInfoDto();
        List<LocationInfoDto> dtos = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        locations.add(location);
        dtos.add(dto);

        when(locationRepository.findAll()).thenReturn(locations);

        assertEquals(dtos, locationService.getAllLocation());
    }

    @Test
    void getLocationById(){
        Location location = Models.createLocation();
        LocationInfoDto dto = Models.getLocationInfoDto();

        when(locationRepository.findById(anyLong())).thenReturn(Optional.of(location));

        assertEquals(dto,locationService.getLocationById(anyLong()));
    }

    @Test
    void deleteLocationById(){
        Location location = Models.createLocation();

        when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));

        locationService.deleteLocationByID(location.getId());

        verify(locationRepository).findById(location.getId());
        verify(locationRepository).delete(location);
    }

    @Test
    void updateLocationById(){
        Location location = Models.createLocation();
        EditLocationDto dto = Models.editLocationDto();

        when(locationRepository.findById(location.getId())).thenReturn(Optional.of(location));

        locationService.updateLocation(location.getId(),dto);

        verify(locationRepository).findById(location.getId());
    }

    @Test
    void createLocationThrowException(){
        AddLocationDto dto = Models.createAddLocationDto();

        when(regionRepository.findById(any())).thenThrow(RegionDoesNotExistException.class);

        assertThrows(RegionDoesNotExistException.class, ()-> locationService.addLocation(dto));
    }

    @Test
    void addLocationServiceWithoutRegion() {
        Location location = Models.createLocation();
        AddLocationDto addLocationDto = Models.createAddLocationDto();
        addLocationDto.setRegionId(null);

        when(regionRepository.findById(0L)).thenReturn(Optional.of(Models.getRegion()));
        when(locationRepository.save(location)).thenReturn(location);

        locationService.addLocation(addLocationDto);

        verify(regionRepository, times(2)).findById(0L);
        verify(locationRepository).save(location);
    }



}
