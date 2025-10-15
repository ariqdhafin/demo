package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Room;
import com.example.demo.Models.dto.RoomDTO;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer>{
    @Query("""
            SELECT 
                new com.example.demo.Models.dto.RoomDTO(r.id, r.name, r.capacity, r.location, r.status)
                FROM
                    Room r
            """)
    public List<RoomDTO> getAll();
    
    @Query("""
            SELECT 
                new com.example.demo.Models.dto.RoomDTO(r.id, r.name, r.capacity, r.location, r.status)
                FROM
                    Room r
                WHERE 
                    r.id = ?1
            """)
    public RoomDTO get(Integer id);
}
