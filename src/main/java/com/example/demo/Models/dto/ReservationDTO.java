package com.example.demo.Models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservationDTO {
    private Integer id;
    private Integer employeeid;
    private Integer roomid;
    private java.sql.Date submitDate;
    private String purpose;
    private java.sql.Date reservationDate;
    private java.sql.Time startTime;
    private java.sql.Time endTime;
    private String approvalStatus;
    private Integer approvedBy;
}
