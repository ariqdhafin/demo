package com.example.demo.Models.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {
    private Integer id;
    private Integer employeeId;
    private Integer roomId;
    private String purpose;
    private LocalDate reservationDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String approvalStatus;
    private Integer approvedBy;
    private LocalDateTime submitDateTime;
    private LocalDateTime updateDateTime;
}
