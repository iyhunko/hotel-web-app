package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.CustomUserDetails;
import com.iyhunko.hotel.models.Payment;
import com.iyhunko.hotel.services.PaymentService;
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
public class PaymentController {

    @Autowired
    private PaymentService service;

    @RequestMapping("/payments")
    public String showListPage(Model model) {
        List<Payment> payments = service.all();

        model.addAttribute("payments", payments);

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
