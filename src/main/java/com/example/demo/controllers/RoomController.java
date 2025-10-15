package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.dto.RoomDTO;
import com.example.demo.services.RoomService;

@Controller
@RequestMapping("room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping
    public String get(Model model){
        model.addAttribute("rooms", roomService.getAll());
        return "room/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String form(Model model, @PathVariable(required = false) Integer id) {
        if (id != null){
            model.addAttribute("roomDTO", roomService.get(id));
        } else {
            model.addAttribute("roomDTO", new RoomDTO());
        }

        return "room/form";
    }

    @PostMapping("save")
    public String save(RoomDTO roomDTO) {
        boolean result = roomService.save(roomDTO);

        if(result){
            return "redirect:/room";
        }
        return "room/form";
    }

    // @GetMapping("edit/{id}") 
    // public String edit(@PathVariable Integer id, Model model) { 
    //     Room room = roomService.get(id);
    //     RoomDTO roomDTO = new RoomDTO();

    //     roomDTO.setId(room.getId());
    //     roomDTO.setName(room.getName());
    //     roomDTO.setCapacity(room.getCapacity());
    //     roomDTO.setLocation(room.getLocation());
    //     roomDTO.setStatus(room.getStatus());

    //     model.addAttribute("roomDTO", roomDTO); 
    //     return "room/form"; 
    // }

    @PostMapping("delete/{id}") 
    public String delete(@PathVariable Integer id) {
        roomService.remove(id);
        return "redirect:/room";
    }
}