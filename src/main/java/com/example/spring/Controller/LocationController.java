package com.example.spring.Controller;

import com.example.spring.Service.LocationService;
import com.example.spring.dto.AddLocationDto;
import com.example.spring.dto.EditLocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/location")
public class LocationController {
    private final LocationService locationService;

    @GetMapping("/create")
    public String newRegion(Model model) {
        AddLocationDto addLocationDto = new AddLocationDto();
        model.addAttribute("dto", addLocationDto);
        return "location/create-location";
    }

    @PostMapping
    public String createRegion(@ModelAttribute("dto") AddLocationDto dto) {
        locationService.addLocation(dto);
        return "redirect:/region/all";
    }

    @GetMapping("/{id}")
    public String getInfoAboutLocation(@PathVariable("id") Long id, Model model) {
        model.addAttribute("location", locationService.getLocationById(id));
        return "location/location-info";
    }

    @GetMapping("/all")
    public String getAllInfoAboutLocation(Model model){
        model.addAttribute("locations",locationService.getAllLocation());
        return "location/all-location";
    }

    @GetMapping("/{id}/update")
    public String updateLocation(Model model, @PathVariable("id") Long id){
        EditLocationDto dto = new EditLocationDto();
        model.addAttribute("dto",dto);
        model.addAttribute("location",locationService.getLocationById(id));
        return "location/edit-location";
    }

    @PostMapping("/{id}")
    public String createLocation(@PathVariable("id") Long id,EditLocationDto dto){
        locationService.updateLocation(id,dto);
        return "redirect:/location/all";
    }

    @PostMapping("/{id}/delete")
    public String deleteLocation(@PathVariable("id") Long id){
        locationService.deleteLocationByID(id);
        return "redirect:/location/all";
    }
}
