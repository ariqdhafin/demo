package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.dto.RoleDTO;
import com.example.demo.services.RoleService;

@RestController
@RequestMapping("api/role")
public class RoleAPI {
    private final RoleService roleService;

    @Autowired
    public RoleAPI(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public RoleDTO get(@RequestHeader(name = "token") String token, @RequestParam(name = "id") Integer id){
        if(!token.equals("")){
            if(token.equals("abcd")){
                return roleService.get(id);
            }
        }
        return null;
    }
}
