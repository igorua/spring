package com.example.spring.RestControllers;

import com.example.spring.Service.RegionService;
import com.example.spring.dto.AddRegionDto;
import com.example.spring.dto.EditRegionDto;
import com.example.spring.dto.RegionInfoDto;
import jdk.jfr.ContentType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/rest/region")
public class RestRegionController {
    private final RegionService regionService;

    @PostMapping()
    public ResponseEntity<HttpStatus> createRegion(@Valid  @RequestBody AddRegionDto dto){
        regionService.addNewRegion(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionInfoDto>> getRegions(){
        return ResponseEntity.status(HttpStatus.OK).body(regionService.getAllRegions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionInfoDto> getRegion(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(regionService.getRegionById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updateRegion(@PathVariable("id") Long id,@Valid @RequestBody EditRegionDto dto){
        regionService.editRegion(id,dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRegion(@PathVariable("id") Long id){
        regionService.deleteRegion(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
