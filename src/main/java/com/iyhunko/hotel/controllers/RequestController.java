package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.CustomUserDetails;
import com.iyhunko.hotel.models.Request;
import com.iyhunko.hotel.models.Room;
import com.iyhunko.hotel.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class RequestController {

    @Autowired
    private RequestService service;

    @RequestMapping("/requests")
    public String showListPage(Model model) {
        List<Request> requests = service.all();

        model.addAttribute("requests", requests);

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
        request.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        request.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        request.setCheckinDate(new Timestamp(System.currentTimeMillis()));
        request.setCheckoutDate(new Timestamp(System.currentTimeMillis()));
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
