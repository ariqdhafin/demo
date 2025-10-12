package com.example.demo.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private String email;
    private String position;

    @ManyToOne
    @JoinColumn(name = "managerid", referencedColumnName = "id")
    private Employee manager;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    public List<Reservation> createdReservations;

    @OneToMany(mappedBy = "approvedBy", fetch = FetchType.LAZY)
    public List<Reservation> approvedReservations;

    @OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
    private User user;
}
