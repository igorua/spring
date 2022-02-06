package com.example.spring.Controller;

import com.example.spring.Service.LocationService;
import com.example.spring.Service.UserService;
import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
@Validated
public class UserController {
    private final UserService userService;
    private final LocationService locationService;

    @GetMapping("/create")
    public String newUser(Model model) {
        CreateUserDto dto = new CreateUserDto();
        model.addAttribute("dto", dto);
        model.addAttribute("locations",locationService.getAllLocation());
        return "user/create-user";
    }

    @PostMapping()
    public String createUser(@Valid @ModelAttribute("dto") CreateUserDto dto) {
        userService.addUser(dto);
        return "redirect:/user/all";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getUser());
        return "user/all-users";
    }

    @GetMapping("/{id}")
    public String getUserById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "user/user-info";
    }

    @GetMapping("{id}/update")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        EditUserDto dto = new EditUserDto();
        model.addAttribute("dto", dto);
        return "user/edit-user";
    }

    @PostMapping("/{id}/update")
    public String editUserById(@PathVariable("id") Long id, @Valid @ModelAttribute("dto") EditUserDto dto) {
        userService.editUser(id, dto);
        return "redirect:/user/all";
    }

    @PostMapping("/{id}/delete")
    public String deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/user/all";
    }
}
