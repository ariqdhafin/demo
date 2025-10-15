package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.dto.RoleDTO;
import com.example.demo.services.RoleService;


@Controller
@RequestMapping("role")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public String get(Model model){
        model.addAttribute("roles", roleService.getAll());
        return "role/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String form(Model model, @PathVariable(required = false) Integer id) {
        if (id != null){
            model.addAttribute("roleDTO", roleService.get(id));
        } else {
            model.addAttribute("roleDTO", new RoleDTO());
        }
        
        return "role/form";
    }

    @PostMapping("save")
    public String save(RoleDTO roleDTO) {
        boolean result = roleService.save(roleDTO);

        if(result){
            return "redirect:/role";
        }
        return "role/form";
    }

    @PostMapping("delete/{id}") 
    public String delete(@PathVariable Integer id) {
        roleService.remove(id);
        return "redirect:/role";
    }
}
