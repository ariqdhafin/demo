package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.Models.Department;
import com.example.demo.Models.Employee;
import com.example.demo.Models.dto.DepartmentDTO;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<DepartmentDTO> getAll() {
        return departmentRepository.getAll();
    }

    public DepartmentDTO get(Integer id) {
        return departmentRepository.get(id);
    }

    public Boolean save(DepartmentDTO departmentDTO){
        Department department = new Department();

        if(departmentDTO.getManagerId() != null){
            Employee employee = employeeRepository.findById(departmentDTO.getManagerId()).orElse(null);
            if (employee == null) {
                return false;
            }

            department.setManager(employee);
        }

        department.setName(departmentDTO.getName());

        department.setCreatedAt(LocalDateTime.now());
        department.setUpdatedAt(LocalDateTime.now());

        departmentRepository.save(department);

        return departmentRepository.findById(department.getId()).isPresent();
    }

    public Boolean update(Integer id, DepartmentDTO departmentDTO){
        Department existingDepartment = departmentRepository.findById(id).orElse(null);

        if(departmentDTO.getManagerId() != null){
            Employee employee = employeeRepository.findById(departmentDTO.getManagerId()).orElse(null);
            if (employee == null) {
                return false;
            }

            existingDepartment.setManager(employee);
        }

        existingDepartment.setName(departmentDTO.getName());

        existingDepartment.setUpdatedAt(LocalDateTime.now());

        departmentRepository.save(existingDepartment);

        return departmentRepository.findById(existingDepartment.getId()).isPresent();
    }
}
