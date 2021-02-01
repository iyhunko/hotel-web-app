package com.iyhunko.hotel.controllers;

import com.iyhunko.hotel.models.Room;
import com.iyhunko.hotel.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService service;

    @RequestMapping(value = "/rooms/save", method = RequestMethod.POST)
    public String saveRoom(@ModelAttribute("room") Room room) {

        room.setPhoto("https://static.wikia.nocookie.net/zelda_gamepedia_en/images/3/35/WW_Link_3.png/revision/latest/scale-to-width-down/213");
        room.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        service.save(room);

        return "redirect:/";
    }

    @RequestMapping("/rooms")
    public String showListPage(Model model) {
        List<Room> listRooms = service.all();

        model.addAttribute("rooms", listRooms);

        return "rooms";
    }

    @RequestMapping("/rooms/create")
    public String showCreatePage(Model model) {
        Room room = new Room();

        model.addAttribute("room", room);

        return "room_create";
    }
}
