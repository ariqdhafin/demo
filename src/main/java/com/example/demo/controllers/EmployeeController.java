package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Models.dto.EmployeeDTO;
import com.example.demo.services.EmployeeService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping
    public String get(Model model){
        model.addAttribute("employees", employeeService.getAll());
        return "employee/index";
    }

    @GetMapping(value = {"form", "form/{id}"})
    public String form(Model model, @PathVariable(required = false) Integer id) {
        if (id != null){
            model.addAttribute("employeeDTO", employeeService.get(id));
        } else {
            model.addAttribute("employeeDTO", new EmployeeDTO());
        }

        return "employee/form";
    }

    @PostMapping("save")
    public String save(EmployeeDTO employeeDTO) {
        boolean result = employeeService.save(employeeDTO);

        if(result){
            return "redirect:/employee";
        }
        return "employee/form";
    }

    @PostMapping("delete/{id}") 
    public String delete(@PathVariable Integer id) {
        employeeService.remove(id);
        return "redirect:/employee";
    }
}
