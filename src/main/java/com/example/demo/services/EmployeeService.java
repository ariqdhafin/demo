package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Employee;
import com.example.demo.Models.Reservation;
import com.example.demo.Models.dto.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.UserRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final UserRepository userRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepository.getAll();
    }

    public EmployeeDTO get(Integer id) {
        return employeeRepository.get(id);
    }

    public Boolean save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setAddress(employeeDTO.getAddress());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPosition(employeeDTO.getPosition());

        if (employeeDTO.getManagerId() != null) {
            Employee manager = employeeRepository.findById(employeeDTO.getManagerId()).orElse(null);
            if (manager == null) {
                return false; 
            }
            employee.setManager(manager);
        }

        employeeRepository.save(employee);

        return employeeRepository.findById(employee.getId()).isPresent();
    }

    public Boolean remove(Integer id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null){
            List<Employee> listEmployees = employeeRepository.findByManager(employee.getManager());
            for(Employee e: listEmployees){
                e.setManager(null);
            }
            for (Reservation r : employee.getApprovedReservations()) {
                r.setApprovedBy(null);
            }
            if(employee.getUser() != null){
                userRepository.deleteById(employee.getUser().getId());
            }
            employeeRepository.deleteById(id);
        }else{
            return false;
        }
        return !employeeRepository.findById(id).isPresent();
    }
}