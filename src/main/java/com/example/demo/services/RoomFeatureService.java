package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.RoomFeature;
import com.example.demo.Models.dto.RoomFeatureDTO;
import com.example.demo.repository.RoomFeatureRepository;

@Service
public class RoomFeatureService {
    private final RoomFeatureRepository roomFeatureRepository;

    @Autowired
    public RoomFeatureService(RoomFeatureRepository roomFeatureRepository) {
        this.roomFeatureRepository = roomFeatureRepository;
    }
    
    public List<RoomFeatureDTO> getAll(){
        return roomFeatureRepository.getAll();
    }

    public RoomFeatureDTO get(Integer id){
        return roomFeatureRepository.get(id);
    }

    public Boolean save(RoomFeatureDTO roomdto){
        RoomFeature roomFeature = new RoomFeature();
        roomFeature.setName(roomdto.getName());
        roomFeature.setDescription(roomdto.getDescription());
        roomFeature.setCreatedAt(LocalDateTime.now());
        roomFeature.setUpdatedAt(LocalDateTime.now());

        roomFeatureRepository.save(roomFeature);

        return roomFeatureRepository.findById(roomFeature.getId()).isPresent();
    }

    public Boolean update(Integer id, RoomFeatureDTO roomdto){
        RoomFeature existingRoomFeature = roomFeatureRepository.findById(id).orElse(null);
        existingRoomFeature.setName(roomdto.getName());
        existingRoomFeature.setDescription(roomdto.getDescription());
        existingRoomFeature.setUpdatedAt(LocalDateTime.now());

        roomFeatureRepository.save(existingRoomFeature);

        return roomFeatureRepository.findById(existingRoomFeature.getId()).isPresent();
    }
}
