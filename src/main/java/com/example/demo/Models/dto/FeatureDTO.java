package com.example.demo.Models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeatureDTO {
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
}