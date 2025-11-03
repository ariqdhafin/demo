package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.RoomFeatureMapping;
import com.example.demo.Models.dto.RoomFeatureMappingDTO;

@Repository
public interface RoomFeatureMappingRepository extends JpaRepository<RoomFeatureMapping, Integer>{
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.RoomFeatureMappingDTO(
                        r.id, 
                        r.room.id, 
                        r.roomFeature.id, 
                        r.quantity,
                        r.createdAt,
                        r.updatedAt
                    )
                FROM
                    RoomFeatureMapping r
            """)
    public List<RoomFeatureMappingDTO> getAll();
    
    @Query("""
                SELECT 
                    new com.example.demo.Models.dto.RoomFeatureMappingDTO(
                        r.id, 
                        r.room.id, 
                        r.roomFeature.id, 
                        r.quantity,
                        r.createdAt,
                        r.updatedAt
                    )
                FROM
                    RoomFeatureMapping r
                WHERE 
                    r.id = ?1
            """)
    public RoomFeatureMappingDTO get(Integer id);
}
