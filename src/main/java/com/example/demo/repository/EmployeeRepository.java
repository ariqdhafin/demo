package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Employee;
import com.example.demo.Models.dto.EmployeeDTO;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    @Query("""
            SELECT 
                new com.example.demo.Models.dto.EmployeeDTO(e.id, e.name, e.address, e.email, e.position, e.manager.Id)
                FROM
                    Employee e
            """)
    public List<EmployeeDTO> getAll();
    
    @Query("""
            SELECT 
                new com.example.demo.Models.dto.EmployeeDTO(e.id, e.name, e.address, e.email, e.position, e.manager.Id)
                FROM
                    Employee e
                WHERE 
                    e.id = ?1
            """)
    public EmployeeDTO get(Integer id);

    List<Employee> findByManager(Employee manager); 
}
