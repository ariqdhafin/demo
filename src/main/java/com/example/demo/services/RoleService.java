package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Role;
import com.example.demo.Models.dto.RoleDTO;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public List<Role> get(){
        return roleRepository.findAll();
    }

    public Role get(Integer id){
        return roleRepository.findById(id).orElse(null);
    }

    public Boolean save(RoleDTO roledto){
        Role role = new Role();
        role.setId(roledto.getId());
        role.setName(roledto.getName());

        roleRepository.save(role);

        return roleRepository.findById(role.getId()).isPresent();
    }

    public Boolean remove(Integer id){
        roleRepository.deleteById(id);

        return !roleRepository.findById(id).isPresent();
    }
}
