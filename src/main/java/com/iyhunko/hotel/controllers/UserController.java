package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.config.CustomUserDetails;
import com.iyhunko.hotel.models.User;
import com.iyhunko.hotel.services.PasswordEncoder;
import com.iyhunko.hotel.services.UserService;
import com.iyhunko.hotel.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

        return "user/users";
    }

    @GetMapping("/profile")
    public String profile(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        model.addAttribute("user", service.find(userDetails.getUser().getId()));

        return "user/profile";
    }

    @RequestMapping(value = "/users/profile", method = RequestMethod.POST)
    public String updateProfile(
            @ModelAttribute("user") User user,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        // TODO: chek if password1 == password2 and return validation error
        // TODO: add validation for all fields

        if (user.getPassword().equals("")) {
            user.setPassword(userDetails.getPassword());
        } else {
            user.setPassword(PasswordEncoder.encodePassword(user.getPassword()));
            user.setRoles(userDetails.getUser().getRoles());
        }

        service.save(user);

        return "redirect:/profile";
    }

    @RequestMapping(value = "/users/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("user") User user,
            BindingResult results
    ) {
        new UserValidator().validate(user, results);
        if (results.hasErrors()) {
            return "user/user_edit";
        }

        service.save(user);

        return "redirect:/users";
    }

    @RequestMapping("/users/{id}/edit")
    public ModelAndView showEditPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("user/user_edit");

        mav.addObject("user", service.find(id));

        return mav;
    }

    @RequestMapping("/users/{id}/delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.delete(id);

        return "redirect:/users";
    }
}
