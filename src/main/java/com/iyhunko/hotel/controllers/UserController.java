package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.config.CustomUserDetails;
import com.iyhunko.hotel.models.User;
import com.iyhunko.hotel.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Autowired
    UserService service;

    int PAGINATION_LIMIT = 5;

    @GetMapping("/users")
    public String index(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", required = false, defaultValue = "DESC") String sortOrder,
            Model model
    ) {
        Page<User> usersWithPagination = service.getWithPagination(page, PAGINATION_LIMIT, sortBy, sortOrder);

        model.addAttribute("users", usersWithPagination.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", usersWithPagination.getTotalPages());
        model.addAttribute("totalItems", usersWithPagination.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("reverseSortOrder", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");
        model.addAttribute("pageUri", "users");

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
        // TODO: add validation
        return "redirect:/user";
    }

}
