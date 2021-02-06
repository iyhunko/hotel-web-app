package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.config.CustomUserDetails;
import com.iyhunko.hotel.models.Payment;
import com.iyhunko.hotel.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;

@Controller
public class PaymentController {

    int PAGINATION_LIMIT = 5;
    @Autowired
    private PaymentService service;

    @RequestMapping("/payments")
    public String index(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", required = false, defaultValue = "DESC") String sortOrder,
            Model model
    ) {
        Page<Payment> requestsWithPagination = service.getWithPagination(page, PAGINATION_LIMIT, sortBy, sortOrder);

        model.addAttribute("payments", requestsWithPagination.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", requestsWithPagination.getTotalPages());
        model.addAttribute("totalItems", requestsWithPagination.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("reverseSortOrder", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");
        model.addAttribute("pageUri", "payments");

        return "payments";
    }

    @RequestMapping("/payments/create")
    public String showCreatePage(Model model) {
        Payment payment = new Payment();

        model.addAttribute("payment", payment);

        return "payment_create";
    }

    @RequestMapping(value = "/payments/save", method = RequestMethod.POST)
    public String save(
            @ModelAttribute("payment") Payment payment,
            @AuthenticationPrincipal CustomUserDetails currentUser
    ) {
        payment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        payment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        payment.setExpireAt(new Timestamp(System.currentTimeMillis()));
        payment.setStatus("created");

        service.save(payment);

        return "redirect:/payments";
    }

    @RequestMapping("/payments/{id}/edit")
    public ModelAndView showEditPage(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("payment_edit");

        Payment payment = service.find(id);

        mav.addObject("payment", payment);

        return mav;
    }

    @RequestMapping("/payments/{id}/delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.delete(id);

        return "redirect:/payments";
    }

}
