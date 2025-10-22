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
                    r.employee.id, 
                    r.room.id, 
                    r.submitDate, 
                    r.purpose,
                    r.reservationDate,
                    r.startTime,
                    r.endTime,
                    r.approvalStatus,
                    r.approvedBy.id
                )
                FROM
                    Reservation r
            """)
    public List<ReservationDTO> getAll();
    
    @Query("""
            SELECT 
                new com.example.demo.Models.dto.ReservationDTO(
                    r.id, 
                    r.employee.id, 
                    r.room.id, 
                    r.submitDate, 
                    r.purpose,
                    r.reservationDate,
                    r.startTime,
                    r.endTime,
                    r.approvalStatus,
                    r.approvedBy.id
                )
                FROM
                    Reservation r
            """)
    public ReservationDTO get(Integer id);
} 
