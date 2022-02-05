package com.example.spring.Service;

import com.example.spring.dto.AddLocationDto;
import com.example.spring.dto.EditLocationDto;
import com.example.spring.dto.LocationInfoDto;

import java.util.List;

public interface LocationService {
    void addLocation(AddLocationDto dto);

    List<LocationInfoDto> getAllLocation();

    LocationInfoDto getLocationById(Long id);

    void deleteLocationByID(Long id);

    void updateLocation(Long id, EditLocationDto dto);
}
