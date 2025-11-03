package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Role;
import com.example.demo.Models.dto.RoleDTO;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.RoleDTO(
                    r.id, 
                    r.name,
                    r.description, 
                    r.createdAt, 
                    r.updatedAt
                )
                FROM
                    Role r
            """)
    public List<RoleDTO> getAll();

    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.RoleDTO(
                        r.id, 
                        r.name,
                        r.description, 
                        r.createdAt, 
                        r.updatedAt
                    )
                FROM
                    Role r
                WHERE 
                    r.id = ?1
            """)
    public RoleDTO get(Integer id);
}
