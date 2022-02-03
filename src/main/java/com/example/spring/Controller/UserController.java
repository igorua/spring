package com.example.spring.Controller;

import com.example.spring.Service.UserService;
import com.example.spring.dto.CreateUserDto;
import com.example.spring.dto.EditUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @PostMapping
    public String createUser(@ModelAttribute("user") CreateUserDto dto){
        userService.addUser(dto);
        return " ";
    }

    @GetMapping
    public String getAllUsers(Model model){
        model.addAttribute("users",userService.getUser());
        return " ";
    }

    @GetMapping("/{id}")
    public String getUserById(Model model,@PathVariable("id") Long id){
        model.addAttribute("user",userService.getUserById(id));
        return " ";
    }

    @PatchMapping("/{id}")
    public String editUserById(Model model, @PathVariable("id") Long id, EditUserDto dto){
        userService.editUser(id,dto);
        return "redirect:/user/{id}";
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/home";
    }
}
