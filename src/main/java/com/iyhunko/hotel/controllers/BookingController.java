package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.config.CustomUserDetails;
import com.iyhunko.hotel.enums.BookingStatus;
import com.iyhunko.hotel.models.Booking;
import com.iyhunko.hotel.services.BookingService;
import com.iyhunko.hotel.services.RoomService;
import com.iyhunko.hotel.validators.SaveBookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class BookingController {

    int PAGINATION_LIMIT = 5;
    @Autowired
    private BookingService service;
    @Autowired
    private RoomService roomService;

    @GetMapping("/bookings")
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

    @GetMapping("/bookings/create")
    public String showCreatePage(Model model) {
        Booking booking = new Booking();

        model.addAttribute("booking", booking);
        model.addAttribute("booking", booking);

        return "booking/booking_create";
    }

    @RequestMapping(value = "/bookings/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("booking") Booking booking,
            @AuthenticationPrincipal CustomUserDetails currentUser,
            BindingResult results
    ) {
        new SaveBookingValidator().validate(booking, results);
        if (results.hasErrors()) {
            return "booking/booking_edit";
        }

        booking.setCheckinDate(new Date(System.currentTimeMillis()));
        booking.setCheckoutDate(new Date(System.currentTimeMillis()));
        booking.setUpdatedAt(new Date(System.currentTimeMillis()));
        if (booking.getUser() == null) {
            booking.setUser(currentUser.getUser());
        }

        service.save(booking);

        return "redirect:/bookings";
    }

    @GetMapping("/bookings/{id}/edit")
    public ModelAndView showEditPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("booking/booking_edit");

        Booking booking = service.find(id);

        mav.addObject("booking", booking);
        mav.addObject("rooms", roomService.all());
        mav.addObject("bookingStatuses", BookingStatus.values());

        return mav;
    }

    @GetMapping("/bookings/{id}/details")
    public ModelAndView showDetailsPage(
            @PathVariable(name = "id") Long id
    ) {
        ModelAndView mav = new ModelAndView("booking/booking_details");

        Booking booking = service.find(id);

        mav.addObject("booking", booking);

        return mav;
    }

    @GetMapping("/bookings/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);

        return "redirect:/bookings";
    }

}
