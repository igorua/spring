package com.example.spring.Controller;

import com.example.spring.ServiceImpl.RegionServiceImpl;
import com.example.spring.dto.AddRegionDto;
import com.example.spring.dto.EditRegionDto;
import com.example.spring.dto.EditUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {
    private final RegionServiceImpl regionService;

    @GetMapping("/create")
    public String newRegion(Model model){
        AddRegionDto addRegionDto = new AddRegionDto();
        model.addAttribute("dto",addRegionDto);
        return "region/add-region";
    }

    @PostMapping
    public String createRegion(Model model, @ModelAttribute("dto") AddRegionDto dto){
        regionService.addNewRegion(dto);
        return "redirect:/region/all";
    }

    @GetMapping("/all")
    public String getAllLocations(Model model){
        model.addAttribute("regions",regionService.getAllRegions());
        return "region/all-region";
    }

    @GetMapping("/{id}")
    public String getDetailInfoAboutRegion(@PathVariable("id") Long id,Model model){
        model.addAttribute("region",regionService.getRegionById(id));
        return "region/region-info";
    }

    @PostMapping("/{id}/delete")
    public String deleteLocationById(@PathVariable("id") Long id){
        regionService.deleteRegion(id);
        return "redirect:/region/all";
    }
    @GetMapping("/{id}/update")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("region", regionService.getRegionById(id));
        EditUserDto dto = new EditUserDto();
        model.addAttribute("dto", dto);
        return "region/edit-region";
    }

    @PostMapping("/{id}/update")
    public String updateLocationByName(@PathVariable("id") Long id, EditRegionDto dto){
        regionService.editRegion(id,dto);
        return "redirect:/region/all";
    }
}
