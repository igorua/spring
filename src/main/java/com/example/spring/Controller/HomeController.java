package com.example.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping({"/", "/home"})
    public String getUser(Model model) {
        model.addAttribute("hello", "Hello and  welcome on the home page !");
        return "home";
    }
}
