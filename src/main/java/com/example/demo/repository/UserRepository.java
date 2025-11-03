package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.User;
import com.example.demo.Models.dto.UserDTO;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.UserDTO(
                        u.id, 
                        u.role.id, 
                        u.employee.id,
                        u.username, 
                        u.password, 
                        u.createdAt,
                        u.updatedAt
                    )
                FROM
                    User u
            """)
    public List<UserDTO> getAll();
    
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.UserDTO(
                        u.id, 
                        u.role.id, 
                        u.employee.id,
                        u.username, 
                        u.password, 
                        u.createdAt,
                        u.updatedAt
                    )                
                FROM
                    User u
                WHERE 
                    u.id = ?1
            """)
    public UserDTO get(Integer id);

    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.UserDTO(
                        u.id, 
                        u.role.id, 
                        u.employee.id,
                        u.username, 
                        u.password, 
                        u.createdAt,
                        u.updatedAt
                    )                
                FROM
                    User u
                WHERE 
                    u.username = ?1
            """)
    public UserDTO findByUsernameDTO(String username);
    
    public User findByUsername(String username);
}
