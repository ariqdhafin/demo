package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Employee;
import com.example.demo.Models.Reservation;
import com.example.demo.Models.Room;
import com.example.demo.Models.dto.ReservationDTO;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final EmployeeRepository employeeRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, EmployeeRepository employeeRepository,RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.employeeRepository = employeeRepository;
        this.roomRepository = roomRepository;
    }

    public List<ReservationDTO> getAll() {
        return reservationRepository.getAll();
    }

    public ReservationDTO get(Integer id) {
        return reservationRepository.get(id);
    }

    public Boolean save(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();

        Employee reservedBy = employeeRepository.findById(reservationDTO.getReservedBy()).orElse(null);
        if (reservedBy == null) {
            return false; 
        }
        reservation.setReservedBy(reservedBy);
        
        reservation.setReviewedBy(null);

        Room room = roomRepository.findById(reservationDTO.getRoomId()).orElse(null);
        if (room == null) {
            return false; 
        }
        reservation.setRoom(room);

        reservation.setPurpose(reservationDTO.getPurpose());
        reservation.setStartDateTime(reservationDTO.getStartDateTime());
        reservation.setEndDateTime(reservationDTO.getEndDateTime());
        reservation.setApprovalStatus("Pending");
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());

        reservationRepository.save(reservation);

        return reservationRepository.findById(reservation.getId()).isPresent();
    }

    public Boolean update(Integer id, ReservationDTO reservationDTO) {
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);
        
        if (reservationDTO.getReviewedBy() != null) {
            Employee reviewedBy = employeeRepository.findById(reservationDTO.getReviewedBy()).orElse(null);
            if (reviewedBy == null) {
                return false; 
            }   
            existingReservation.setReviewedBy(reviewedBy);
        }

        existingReservation.setApprovalStatus(reservationDTO.getApprovalStatus());
        
        existingReservation.setUpdatedAt(LocalDateTime.now());

        reservationRepository.save(existingReservation);

        return reservationRepository.findById(existingReservation.getId()).isPresent();
    }

    // public Boolean remove(Integer id) {
    //     Reservation reservation = reservationRepository.findById(id).orElse(null);
    //     if (reservation != null) {
    //         reservationRepository.deleteById(id);
    //     }else{
    //         return false;
    //     }
    //     return !reservationRepository.findById(id).isPresent();
    // }
}
