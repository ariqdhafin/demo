package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.dto.UserDTO;
import com.example.demo.services.UserService;

@Controller
@RequestMapping("user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public String get(Model model){
        model.addAttribute("users", userService.getAll());
        return "user/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String form(Model model, @PathVariable(required = false) Integer id) {
        if (id != null){
            model.addAttribute("userDTO", userService.get(id));
        } else {
            model.addAttribute("userDTO", new UserDTO());
        }
        return "user/form";
    }

    @PostMapping("save")
    public String save(UserDTO userDTO) {
        boolean result = userService.save(userDTO);

        if(result){
            return "redirect:/user";
        }
        return "user/form";
    }

    // @GetMapping("edit/{id}") 
    // public String edit(@PathVariable Integer id, Model model) { 
    //     User user = userService.get(id);
    //     UserDTO userDTO = new UserDTO();

    //     userDTO.setId(user.getId());
    //     userDTO.setUsername(user.getUsername());
    //     userDTO.setPassword(user.getPassword());
    //     if (user.getRole() != null) {
    //         userDTO.setRoleId(user.getRole().getId());
    //     } else {
    //         userDTO.setRoleId(null);
    //     }

    //     model.addAttribute("userDTO", userDTO); 
    //     return "user/form"; 
    // }

    // @PostMapping("delete/{id}") 
    // public String delete(@PathVariable Integer id) {
    //     userService.remove(id);
    //     return "redirect:/user";
    // }
}
