package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.config.CustomUserDetails;
import com.iyhunko.hotel.models.Booking;
import com.iyhunko.hotel.models.Request;
import com.iyhunko.hotel.models.Room;
import com.iyhunko.hotel.services.BookingService;
import com.iyhunko.hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService service;
    @Autowired
    private RoomService roomService;

    int PAGINATION_LIMIT = 5;

    public String index(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", required = false, defaultValue = "DESC") String sortOrder,
            Model model
    ) {
        Page<Booking> requestsWithPagination = service.getWithPagination(page, PAGINATION_LIMIT, sortBy, sortOrder);

        model.addAttribute("bookings", requestsWithPagination.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestsWithPagination.getTotalPages());
        model.addAttribute("totalItems", requestsWithPagination.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("reverseSortOrder", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");
        model.addAttribute("pageUri", "bookings");

        return "booking/bookings";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        Booking booking = new Booking();

        model.addAttribute("booking", booking);

        return "booking/booking_create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("booking") Booking booking,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        booking.setCheckinDate(new Date(System.currentTimeMillis()));
        booking.setCheckoutDate(new Date(System.currentTimeMillis()));
        booking.setUpdatedAt(new Date(System.currentTimeMillis()));
        booking.setUserId(currentUser.getUser().getId());

        service.save(booking);

        return "redirect:/bookings";
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("booking/booking_edit");

        Booking booking = service.find(id);

        mav.addObject("booking", booking);
        mav.addObject("rooms",  roomService.all());

        return mav;
    }

    @GetMapping("/{id}/details")
    public ModelAndView showDetailsPage(
            @PathVariable(name = "id") Long id
    ) {
        ModelAndView mav = new ModelAndView("booking/booking_details");

        Booking booking = service.find(id);

        mav.addObject("booking", booking);

        return mav;
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);

        return "redirect:/bookings";
    }

}
