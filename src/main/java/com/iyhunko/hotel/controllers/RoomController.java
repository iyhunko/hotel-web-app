package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.models.Room;
import com.iyhunko.hotel.models.User;
import com.iyhunko.hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
public class RoomController {

    @Autowired
    private RoomService service;

    int PAGINATION_LIMIT = 5;

    @RequestMapping("/rooms")
    public String index(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortOrder", required = false, defaultValue = "DESC") String sortOrder,
            Model model,
            @RequestParam Map<String,String> requestParams
    ) {
        Page<Room> roomsWithPagination = service.getWithPagination(page, PAGINATION_LIMIT, sortBy, sortOrder, requestParams);

        model.addAttribute("rooms", roomsWithPagination.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", roomsWithPagination.getTotalPages());
        model.addAttribute("totalItems", roomsWithPagination.getTotalElements());
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortOrder", sortOrder);
        model.addAttribute("reverseSortOrder", sortOrder.equalsIgnoreCase("asc") ? "desc" : "asc");
        model.addAttribute("pageUri", "rooms");

        return "rooms";
    }

    @RequestMapping(value = "/rooms/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("room") Room room) {
        room.setPhoto("https://static.wikia.nocookie.net/zelda_gamepedia_en/images/3/35/WW_Link_3.png/revision/latest/scale-to-width-down/213");
        room.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        service.save(room);

        return "redirect:/rooms";
    }

    @RequestMapping("/rooms/create")
    public String showCreatePage(Model model) {
        Room room = new Room();

        model.addAttribute("room", room);

        return "room_create";
    }

    @RequestMapping("/rooms/{id}/edit")
    public ModelAndView showEditPage(
            @PathVariable(name = "id") Long id
    ) {
        ModelAndView mav = new ModelAndView("room_edit");

        Room room = service.find(id);

        mav.addObject("room", room);

        return mav;
    }

    @RequestMapping("/rooms/{id}/details")
    public ModelAndView showDetailsPage(
            @PathVariable(name = "id") Long id
    ) {
        ModelAndView mav = new ModelAndView("room_details");

        Room room = service.find(id);

        mav.addObject("room", room);

        return mav;
    }

    @RequestMapping("/rooms/{id}/delete")
    public String delete(@PathVariable(name = "id") Long id) {
        service.delete(id);

        return "redirect:/rooms";
    }
}
