package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.config.CustomUserDetails;
import com.iyhunko.hotel.models.Booking;
import com.iyhunko.hotel.services.BookingService;
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
public class BookingController {

    @Autowired
    private BookingService service;

    @RequestMapping("/bookings")
    public String showListPage(Model model) {
        List<Booking> bookings = service.all();

        model.addAttribute("bookings", bookings);

        return "bookings";
    }

    @RequestMapping("/bookings/create")
    public String showCreatePage(Model model) {
        Booking booking = new Booking();

        model.addAttribute("booking", booking);

        return "booking_create";
    }

    @RequestMapping(value = "/bookings/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("booking") Booking booking,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        booking.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        booking.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        booking.setCheckinDate(new Timestamp(System.currentTimeMillis()));
        booking.setCheckoutDate(new Timestamp(System.currentTimeMillis()));
        booking.setUserId(currentUser.getUser().getId());

        service.save(booking);

        return "redirect:/bookings";
    }

    @RequestMapping("/bookings/{id}/edit")
    public ModelAndView showEditPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("booking_edit");

        Booking booking = service.find(id);

        mav.addObject("booking", booking);

        return mav;
    }

    @RequestMapping("/bookings/{id}/details")
    public ModelAndView showDetailsPage(
            @PathVariable(name = "id") Long id
    ) {
        ModelAndView mav = new ModelAndView("booking_details");

        Booking booking = service.find(id);

        mav.addObject("booking", booking);

        return mav;
    }

    @RequestMapping("/bookings/{id}/delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.delete(id);

        return "redirect:/bookings";
    }

}
