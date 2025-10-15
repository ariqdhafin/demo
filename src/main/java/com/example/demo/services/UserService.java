package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Employee;
import com.example.demo.Models.Role;
import com.example.demo.Models.User;
import com.example.demo.Models.dto.UserDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
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

        Role role = roleRepository.findById(userDTO.getRoleId()).orElse(null);
        if (role == null) {
            return false; 
        }
        user.setRole(role);

        Employee employee = employeeRepository.findById(userDTO.getEmployeeId()).orElse(null);
        if (employee == null) {
            return false; 
        }
        user.setEmployee(employee);

        userRepository.save(user);

        return userRepository.findById(user.getId()).isPresent();
    }

    public Boolean remove(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.getEmployee() != null) {
                user.getEmployee().setUser(null);
                user.setEmployee(null);
            }
            if (user.getRole() != null) {
                user.getRole().getUsers().remove(user);
                user.setRole(null);
            }
            userRepository.delete(user);
            return true;
        }   
        return !userRepository.findById(id).isPresent();
    }
}
