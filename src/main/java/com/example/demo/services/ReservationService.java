package com.example.demo.services;

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
        reservation.setId(reservationDTO.getId());

        Employee employee = employeeRepository.findById(reservationDTO.getEmployeeId()).orElse(null);
        if (employee == null) {
            return false; 
        }
        reservation.setEmployee(employee);

        Room room = roomRepository.findById(reservationDTO.getRoomId()).orElse(null);
        if (room == null) {
            return false; 
        }
        reservation.setRoom(room);

        reservation.setSubmitDate(reservationDTO.getSubmitDate());
        reservation.setPurpose(reservationDTO.getPurpose());
        reservation.setReservationDate(reservationDTO.getReservationDate());
        reservation.setStartTime(reservationDTO.getStartTime());
        reservation.setEndTime(reservationDTO.getEndTime());
        reservation.setApprovalStatus(reservationDTO.getApprovalStatus());

        if (reservationDTO.getApprovedBy() != null) {
            Employee approveBy = employeeRepository.findById(reservationDTO.getApprovedBy()).orElse(null);
            if (approveBy  == null) {
                return false; 
            }
            reservation.setApprovedBy(approveBy);
        }

        reservationRepository.save(reservation);

        return reservationRepository.findById(reservation.getId()).isPresent();
    }

    public Boolean remove(Integer id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation.getEmployee() != null) {
            reservation.getEmployee().getCreatedReservations().remove(reservation);
            reservation.getEmployee().getApprovedReservations().remove(reservation);
        }
        if (reservation.getRoom() != null) {
            reservation.getRoom().getReservations().remove(reservation);
            reservation.setRoom(null);
        }
        reservationRepository.deleteById(id);
        return !reservationRepository.findById(id).isPresent();
    }
}
