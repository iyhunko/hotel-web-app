package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.models.User;
import com.iyhunko.hotel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/list_users")
    public String index(Model model) {
        List<User> users =  repository.findAll();

        model.addAttribute("users", users);

        return "users";
    }

}
