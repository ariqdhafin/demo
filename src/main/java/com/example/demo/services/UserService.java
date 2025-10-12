package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Models.dto.UserDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User get(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public Boolean save(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        Role role = roleRepository.findById(userDTO.getRoleid()).orElse(null);
        if (role == null) {
            return false; 
        }
        user.setRole(role);

        userRepository.save(user);

        return userRepository.findById(user.getId()).isPresent();
    }

    // Delete a user by ID
    public Boolean remove(Integer id) {
        userRepository.deleteById(id);
        return !userRepository.findById(id).isPresent();
    }
}
