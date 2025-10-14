package com.example.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employeeId", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "id")
    private Room room;

    private java.sql.Date submitDate;
    private String purpose;
    private java.sql.Date reservationDate;
    private java.sql.Time startTime;
    private java.sql.Time endTime;
    private String approvalStatus;

    @ManyToOne
    @JoinColumn(name = "approvedBy", referencedColumnName = "id")
    private Employee approvedBy;
}
