package com.example.demo.Models.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private Integer departmenId;
    private String name;
    private String email;
    private String position;
    private Boolean isActive;
    private LocalDateTime createdAt; 
    private LocalDateTime updatedAt; 
}
