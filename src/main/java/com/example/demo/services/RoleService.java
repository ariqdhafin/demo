package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Models.dto.RoleDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<RoleDTO> getAll(){
        return roleRepository.getAll();
    }

    public RoleDTO get(Integer id){
        return roleRepository.get(id);
    }

    public Boolean save(RoleDTO roledto){
        Role role = new Role();

        role.setName(roledto.getName());
        role.setDescription(roledto.getDescription());
        role.setCreatedAt(LocalDateTime.now());
        role.setUpdatedAt(LocalDateTime.now());

        roleRepository.save(role);

        return roleRepository.findById(role.getId()).isPresent();
    }

    public Boolean update(Integer id, RoleDTO roleDTO){
        Role role = roleRepository.findById(id).orElse(null);

        role.setName(roleDTO.getName());
        role.setDescription(roleDTO.getDescription());
        role.setUpdatedAt(LocalDateTime.now());

        roleRepository.save(role);

        return roleRepository.findById(role.getId()).isPresent();
    }

    public Boolean remove(Integer id){
        Role role = roleRepository.findById(id).orElse(null);
        if (role != null) {
            List<User> listUsers = role.getUsers();
            for(User user: listUsers){
                user.setRole(null);
                userRepository.save(user);
            }
        }else{
            return false;
        }

        roleRepository.deleteById(id);

        return !roleRepository.findById(id).isPresent();
    }
}
