package com.example.demo.services;

import java.time.LocalDateTime;
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
    public UserService(UserRepository userRepository, RoleRepository roleRepository,
            EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<UserDTO> getAll() {
        return userRepository.getAll();
    }

    public UserDTO get(Integer id) {
        return userRepository.get(id);
    }

    public UserDTO getByUsername(String username){
        return userRepository.findByUsernameDTO(username);
    }

    public Boolean save(UserDTO userDTO) {
        User user = new User();
        
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());

        if (userDTO.getRoleId() != null) {
            Role role = roleRepository.findById(userDTO.getRoleId()).orElse(null);
            if (role == null) {
                return false; 
            }
            user.setRole(role);
        }

        Employee employee = employeeRepository.findById(userDTO.getEmployeeId()).orElse(null);
        if (employee == null) {
            return false; 
        }
        user.setEmployee(employee);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);

        return userRepository.findById(user.getId()).isPresent();
    }

    public Boolean update(Integer id, UserDTO userDTO){
        User existingUser = userRepository.findById(id).orElse(null);

        existingUser.setUsername(userDTO.getUsername());

        if (userDTO.getRoleId() != null) {
            Role role = roleRepository.findById(userDTO.getRoleId()).orElse(null);
            if (role == null) {
                return false; 
            }
            existingUser.setRole(role);
        }

        existingUser.setUpdatedAt(LocalDateTime.now());

        userRepository.save(existingUser);

        return userRepository.findById(existingUser.getId()).isPresent();
    }

    // public Boolean remove(Integer id) {
    //     User user = userRepository.findById(id).orElse(null);
    //     if (user != null) {
    //         Employee employee = user.getEmployee();
    //         if (employee != null){
    //             List<Employee> listEmployees = employeeRepository.findByManager(employee.getManager());
    //             for(Employee e: listEmployees){
    //                 e.setManager(null);
    //             }
    //             for (Reservation r : employee.getApprovedReservations()) {
    //                 r.setApprovedBy(null);
    //             }
    //             if(employee.getUser() != null){
    //                 userRepository.deleteById(employee.getUser().getId());
    //             }
    //         }else{
    //             return false;
    //         }
    //         userRepository.deleteById(id);
    //     }else{
    //         return false;
    //     }
    //     return !userRepository.findById(id).isPresent();
    // }
}
