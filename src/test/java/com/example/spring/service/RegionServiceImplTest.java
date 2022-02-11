package com.example.spring.service;

import com.example.spring.ServiceImpl.RegionServiceImpl;
import com.example.spring.dao.Entity.Region;
import com.example.spring.dao.Repository.RegionRepository;
import com.example.spring.dto.AddRegionDto;
import com.example.spring.dto.EditRegionDto;
import com.example.spring.dto.RegionInfoDto;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegionServiceImplTest {
    @InjectMocks
    RegionServiceImpl regionService;
    @Mock
    RegionRepository regionRepository;

    @Test
    void addNewRegion(){
        AddRegionDto dto = Models.getAddRegionDto();
        Region region = new Region();
        region.setName("Name");

        when(regionRepository.save(region)).thenReturn(region);

        regionService.addNewRegion(dto);

        verify(regionRepository).save(region);
    }

    @Test
    void getAllRegions() {
        Region region = Models.getRegionInf();
        RegionInfoDto regionInfoDto = Models.getRegionINfoDto();
        List<RegionInfoDto> dtos = new ArrayList<>();
        dtos.add(regionInfoDto);
        when(regionRepository.findAll()).thenReturn(List.of(region));

        assertEquals(dtos, regionService.getAllRegions());
    }

    @Test
    void editRegion(){
        Region region = Models.getRegion();
        EditRegionDto editRegionDto = Models.getEditRegionDto();
        region.setName(editRegionDto.getName());


        when(regionRepository.findById(region.getId())).thenReturn(Optional.ofNullable(Models.getRegion()));

        regionService.editRegion(region.getId(),editRegionDto);

        regionRepository.save(region);
    }

    @Test
    void deleteRegion(){
        Region region = Models.getRegion();

        when(regionRepository.findById(region.getId())).thenReturn(Optional.of(region));

        regionService.deleteRegion(region.getId());

        verify(regionRepository).findById(region.getId());
        verify(regionRepository).delete(region);
    }

    @Test
    void  getRegionById(){
        RegionInfoDto dto = Models.getRegionINfoDto();
        Region region = Models.getRegionInf();

        when(regionRepository.findById(region.getId())).thenReturn(Optional.of(region));

        assertEquals(dto,regionService.getRegionById(region.getId()));
    }
}
