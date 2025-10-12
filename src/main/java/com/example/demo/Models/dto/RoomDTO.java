package com.example.demo.Models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    private Integer id;
    private String name;
    private Integer capacity;
    private String location;
    private String status;
}
