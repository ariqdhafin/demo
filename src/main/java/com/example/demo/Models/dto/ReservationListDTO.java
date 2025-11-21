package com.example.demo.Models.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationListDTO {
    private Integer id;
    private String reservedBy;
    private String reviewedBy;
    private String roomName;
    private String purpose;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String approvalStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
