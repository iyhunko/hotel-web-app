package com.iyhunko.hotel.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Main page");

        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About page");

        return "about";
    }

    @GetMapping("/user")
    public String user(Model model) {
        model.addAttribute("title", "User page");

        return "user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("title", "Admin page");

        return "admin";
    }

}
