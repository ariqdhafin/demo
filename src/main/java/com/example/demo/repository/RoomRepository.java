package com.example.demo.repository;

import java.time.LocalDateTime;
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
                    new com.example.demo.Models.dto.RoomDTO(
                        r.id, 
                        r.name, 
                        r.location, 
                        r.capacity, 
                        r.status,
                        r.imageUrl,
                        r.createdAt,
                        r.updatedAt
                    )
                FROM
                    Room r
            """)
    public List<RoomDTO> getAll();
    
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.RoomDTO(
                        r.id, 
                        r.name, 
                        r.location, 
                        r.capacity, 
                        r.status,
                        r.imageUrl,
                        r.createdAt,
                        r.updatedAt
                    )
                FROM
                    Room r
                WHERE 
                    r.id = ?1
            """)
    public RoomDTO get(Integer id);

    @Query("""
            SELECT
                new com.example.demo.Models.dto.RoomDTO(
                    r.id, 
                    r.name, 
                    r.location, 
                    r.capacity, 
                    r.status,
                    r.imageUrl,
                    r.createdAt,
                    r.updatedAt
                ) 
            FROM
                Room r
            WHERE
                r.status = 'Available'
            AND r.id NOT IN (
                    SELECT 
                        re.room.id 
                    FROM
                        Reservation re
                    WHERE 
                        re.approvalStatus IN ('Approved','Pending')
                    AND
                        (
                            re.startDateTime < :endDateTime AND re.endDateTime > :startDateTime
                        )
                )
            """)
    public List<RoomDTO> getAvailableRoom(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
