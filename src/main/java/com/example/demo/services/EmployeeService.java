package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Employee;
import com.example.demo.Models.dto.EmployeeDTO;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee get(Integer id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Boolean save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        employee.setAddress(employeeDTO.getAddress());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPosition(employeeDTO.getPosition());

        if (employeeDTO.getManagerid() != null) {
            Employee manager = employeeRepository.findById(employeeDTO.getManagerid()).orElse(null);
            if (manager == null) {
                return false; 
            }
            employee.setManager(manager);
        }

        employeeRepository.save(employee);

        return employeeRepository.findById(employee.getId()).isPresent();
    }

    public Boolean remove(Integer id) {
        employeeRepository.deleteById(id);
        return !employeeRepository.findById(id).isPresent();
    }
}