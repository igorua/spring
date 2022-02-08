package com.example.spring.RestControllers;

import com.example.spring.Service.LocationService;
import com.example.spring.dto.AddLocationDto;
import com.example.spring.dto.EditLocationDto;
import com.example.spring.dto.LocationInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/location")
public class RestLocationController {
    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<HttpStatus> createLocation(@RequestBody AddLocationDto dto){
        locationService.addLocation(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<LocationInfoDto>> getLocations(){
        return ResponseEntity.status(HttpStatus.OK).body(locationService.getAllLocation());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationInfoDto> getLocation(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(locationService.getLocationById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateLocation(@PathVariable("id") Long id, @RequestBody EditLocationDto dto){
        locationService.updateLocation(id,dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<HttpStatus> deleteLocation(@PathVariable("id") Long id){
        locationService.deleteLocationByID(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
