package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.CustomUserDetails;
import com.iyhunko.hotel.models.User;
import com.iyhunko.hotel.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    UserRepository repository;

    @GetMapping("/users")
    public String index(Model model) {
        List<User> users = (List<User>) repository.findAll();

        model.addAttribute("users", users);

        return "users";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userDetails.getUser();

        model.addAttribute("email", user.getEmail());
        model.addAttribute("firstname", user.getFirstname());
        model.addAttribute("lastname", user.getLastname());
        model.addAttribute("phone", user.getPhone());

        return "profile";
    }

    @PutMapping("/users")
    public String updateProfile(
            @RequestParam Map<String, String> form
    ) {
        return "redirect:/user";
    }

}
