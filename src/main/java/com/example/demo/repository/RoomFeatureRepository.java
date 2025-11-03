package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.RoomFeature;
import com.example.demo.Models.dto.RoomFeatureDTO;

@Repository
public interface RoomFeatureRepository extends JpaRepository<RoomFeature, Integer>{
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.RoomFeatureDTO(
                        r.id, 
                        r.name, 
                        r.description, 
                        r.createdAt,
                        r.updatedAt
                    )
                FROM
                    RoomFeature r
            """)
    public List<RoomFeatureDTO> getAll();
    
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.RoomFeatureDTO(
                        r.id, 
                        r.name, 
                        r.description, 
                        r.createdAt,
                        r.updatedAt
                    )
                FROM
                    RoomFeature r
                WHERE 
                    r.id = ?1
            """)
    public RoomFeatureDTO get(Integer id);
}
