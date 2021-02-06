package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.config.CustomUserDetails;
import com.iyhunko.hotel.models.Request;
import com.iyhunko.hotel.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Date;

@Controller
public class RequestController {

    int PAGINATION_LIMIT = 5;
    @Autowired
    private RequestService service;

    @RequestMapping("/requests")
    public String index(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", required = false, defaultValue = "DESC") String sortOrder,
            Model model
    ) {
        Page<Request> requestsWithPagination = service.getWithPagination(page, PAGINATION_LIMIT, sortBy, sortOrder);

        model.addAttribute("requests", requestsWithPagination.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestsWithPagination.getTotalPages());
        model.addAttribute("totalItems", requestsWithPagination.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("reverseSortOrder", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");
        model.addAttribute("pageUri", "requests");

        return "requests";
    }

    @RequestMapping("/requests/create")
    public String showCreatePage(Model model) {
        Request room = new Request();

        model.addAttribute("request", room);

        return "request_create";
    }

    @RequestMapping(value = "/requests/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("request") Request request,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        request.setCheckinDate(new Date(System.currentTimeMillis()));
        request.setCheckoutDate(new Date(System.currentTimeMillis()));
        request.setUserId(currentUser.getUser().getId());

        service.save(request);

        return "redirect:/requests";
    }

    @RequestMapping("/requests/{id}/edit")
    public ModelAndView showEditPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("request_edit");

        Request request = service.find(id);

        mav.addObject("request", request);

        return mav;
    }

    @RequestMapping("/requests/{id}/details")
    public ModelAndView showDetailsPage(
            @PathVariable(name = "id") Long id
    ) {
        ModelAndView mav = new ModelAndView("request_details");

        Request request = service.find(id);

        mav.addObject("request", request);

        return mav;
    }

    @RequestMapping("/requests/{id}/delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.delete(id);

        return "redirect:/requests";
    }

}
