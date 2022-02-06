package com.example.spring.ServiceImpl;

import com.example.spring.Service.LocationService;
import com.example.spring.dao.Entity.Location;
import com.example.spring.dao.Entity.Region;
import com.example.spring.dao.Repository.LocationRepository;
import com.example.spring.dao.Repository.RegionRepository;
import com.example.spring.dto.AddLocationDto;
import com.example.spring.dto.EditLocationDto;
import com.example.spring.dto.LocationInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final RegionRepository regionRepository;

    @Override
    public void addLocation(AddLocationDto dto) {
        Location location = mapLocation(dto);
        locationRepository.save(location);
    }

    private Location mapLocation(AddLocationDto dto) {
        Location location = new Location();
        location.setName(dto.getLocationName());
        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        if (dto.getRegionId() == null) {
            dto.setRegionId(0L);
        }
        Region region = new Region();
        region.setName(dto.getRegionName());
        Region region1 = regionRepository.findById(dto.getRegionId()).isPresent() ? regionRepository.findById(dto.getRegionId()).orElseThrow() : region;
        location.setRegion(region1);
        return location;
    }

    @Override
    public List<LocationInfoDto> getAllLocation() {
        return locationRepository.findAll()
                .stream()
                .map(location -> LocationInfoDto.builder()
                        .id(location.getId())
                        .name(location.getName())
                        .longitude(location.getLongitude())
                        .latitude(location.getLatitude())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public LocationInfoDto getLocationById(Long id) {
        Location location = findLocationByID(id);
        return mapLocation(location);
    }

    private LocationInfoDto mapLocation(Location location) {
        return LocationInfoDto.builder()
                .id(location.getId())
                .name(location.getName())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }

    private Location findLocationByID(Long id) {
        return locationRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteLocationByID(Long id) {
        locationRepository.delete(locationRepository.findById(id).orElseThrow());
    }

    public void updateLocation(Long id, EditLocationDto dto){
        Location location = findLocationByID(id);
        location.setName(dto.getName()!=null ? dto.getName() : location.getName());
        location.setLongitude(dto.getLongitude()!=null ? dto.getLongitude() : location.getLongitude());
        location.setLatitude(dto.getLatitude()!=null?dto.getLatitude():location.getLatitude());
        locationRepository.save(location);
    }
}
