package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Department;
import com.example.demo.Models.Employee;
import com.example.demo.Models.dto.EmployeeDTO;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepository.getAll();
    }

    public EmployeeDTO get(Integer id) {
        return employeeRepository.get(id);
    }

    public Boolean save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        if (employeeDTO.getDepartmenId()!= null) {
            Department department = departmentRepository.findById(employeeDTO.getDepartmenId()).orElse(null);
            if (department == null) {
                return false; 
            }
            employee.setDepartment(department);;
        }else{
            return false;
        }

        employee.setName(employeeDTO.getName());
        
        employee.setEmail(employeeDTO.getEmail());

        employee.setPosition(employeeDTO.getPosition());

        employee.setIsActive(true);

        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());

        employeeRepository.save(employee);

        return employeeRepository.findById(employee.getId()).isPresent();
    }

    public boolean update(Integer id, EmployeeDTO employeeDTO){
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (employeeDTO.getDepartmenId()!= null) {
            Department department = departmentRepository.findById(employeeDTO.getDepartmenId()).orElse(null);
            if (department == null) {
                return false; 
            }
            existingEmployee.setDepartment(department);;
        }else{
            return false;
        }

        existingEmployee.setName(employeeDTO.getName());
        
        existingEmployee.setEmail(employeeDTO.getEmail());

        existingEmployee.setPosition(employeeDTO.getPosition());   
        
        existingEmployee.setUpdatedAt(LocalDateTime.now());

        employeeRepository.save(existingEmployee);

        return employeeRepository.findById(existingEmployee.getId()).isPresent();
    }

    public Boolean remove(Integer id) {
        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        existingEmployee.setIsActive(false);

        employeeRepository.save(existingEmployee);

        return true;
    }
}