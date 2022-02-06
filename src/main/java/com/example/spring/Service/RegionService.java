package com.example.spring.Service;

import com.example.spring.dto.AddRegionDto;
import com.example.spring.dto.EditRegionDto;
import com.example.spring.dto.RegionInfoDto;

import java.util.List;

public interface RegionService {
    void addNewRegion(AddRegionDto dto);

    List<RegionInfoDto> getAllRegions();

    void editRegion(Long id, EditRegionDto dto);

    void deleteRegion(Long id);

    RegionInfoDto getRegionById(Long id);
}
