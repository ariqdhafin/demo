package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Department;
import com.example.demo.Models.dto.DepartmentDTO;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.DepartmentDTO(
                        d.id, 
                        d.manager.id,
                        d.name, 
                        d.createdAt,
                        d.updatedAt
                    )
                FROM
                    Department d
            """)
    public List<DepartmentDTO> getAll();
    
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.DepartmentDTO(
                        d.id, 
                        d.manager.id,
                        d.name, 
                        d.createdAt,
                        d.updatedAt
                    )
                FROM
                    Department d
                WHERE 
                    d.id = ?1
            """)
    public DepartmentDTO get(Integer id);

}
