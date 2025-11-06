package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.Reservation;
import com.example.demo.Models.dto.ReservationDTO;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
    @Query("""
            SELECT 
                new com.example.demo.Models.dto.ReservationDTO(
                    r.id, 
                    r.reservedBy.id, 
                    r.reviewedBy.id,
                    r.room.id,  
                    r.purpose,
                    r.startDateTime,
                    r.endDateTime,
                    r.approvalStatus,
                    r.createdAt,
                    r.updatedAt
                )
            FROM
                    Reservation r
            """)
    public List<ReservationDTO> getAll();
    
    @Query("""
            SELECT 
                new com.example.demo.Models.dto.ReservationDTO(
                    r.id, 
                    r.reservedBy.id, 
                    r.reviewedBy.id,
                    r.room.id,  
                    r.purpose,
                    r.startDateTime,
                    r.endDateTime,
                    r.approvalStatus,
                    r.createdAt,
                    r.updatedAt
                )
            FROM
                    Reservation r
            WHERE
                    r.id = ?1
            """)
    public ReservationDTO get(Integer id);

    @Query("""
            SELECT 
                new com.example.demo.Models.dto.ReservationDTO(
                    r.id, 
                    r.reservedBy.id, 
                    r.reviewedBy.id,
                    r.room.id,  
                    r.purpose,
                    r.startDateTime,
                    r.endDateTime,
                    r.approvalStatus,
                    r.createdAt,
                    r.updatedAt
                )
            FROM
                    Reservation r
            WHERE
                    r.reservedBy.id = ?1
            """)
    public List<ReservationDTO> getByReservedById(Integer id);
} 
