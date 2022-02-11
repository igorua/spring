package com.example.spring.ServiceImpl;

import com.example.spring.Service.RegionService;
import com.example.spring.dao.Entity.Region;
import com.example.spring.dao.Repository.RegionRepository;
import com.example.spring.dto.AddRegionDto;
import com.example.spring.dto.EditRegionDto;
import com.example.spring.dto.LocationInfoDto;
import com.example.spring.dto.RegionInfoDto;
import com.example.spring.exception.RegionDoesNotExistException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    @Override
    public void addNewRegion(AddRegionDto dto) {
        Region region = mapDtoToRegion(dto);
        regionRepository.save(region);
    }

    private Region mapDtoToRegion(AddRegionDto dto) {
        Region region = new Region();
        region.setName(dto.getName());
        return region;
    }

    @Override
    public List<RegionInfoDto> getAllRegions() {
        return regionRepository.findAll().stream()
                .map(region -> RegionInfoDto.builder()
                        .id(region.getId())
                        .name(region.getName())
                        .locationInfoDtoList(region.getLocations().stream().map(
                                        location -> LocationInfoDto.builder()
                                                .id(location.getId())
                                                .name(location.getName())
                                                .latitude(location.getLatitude())
                                                .longitude(location.getLongitude())
                                                .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void editRegion(Long id, EditRegionDto dto) {
        Region region = regionRepository.findById(id).orElseThrow(() -> new RegionDoesNotExistException(
                "Region with id: " + id + " does not exist"));
        region.setName(dto.getName());
        regionRepository.save(region);
    }

    @Override
    public void deleteRegion(Long id) {
        Region region = regionRepository.findById(id).orElseThrow(() -> new RegionDoesNotExistException(
                "Region with id: " + id + " does not exist"));
        regionRepository.delete(region);
    }

    @Override
    public RegionInfoDto getRegionById(Long id) {
        RegionInfoDto dto = new RegionInfoDto();
        Region region = regionRepository.findById(id).orElseThrow(() -> new RegionDoesNotExistException(
                "Region with id: " + id + " does not exist"));
        dto.setId(region.getId());
        dto.setName(region.getName());
        List<LocationInfoDto> dtos = region.getLocations().stream()
                .map(location -> LocationInfoDto.builder()
                        .longitude(location.getLongitude())
                        .latitude(location.getLatitude())
                        .name(location.getName())
                        .id(location.getId())
                        .build())
                .collect(Collectors.toList());
        dto.setLocationInfoDtoList(dtos);
        return dto;
    }
}
