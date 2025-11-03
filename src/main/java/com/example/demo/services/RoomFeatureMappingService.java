package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Room;
import com.example.demo.Models.RoomFeature;
import com.example.demo.Models.RoomFeatureMapping;
import com.example.demo.Models.dto.RoomFeatureMappingDTO;
import com.example.demo.repository.RoomFeatureMappingRepository;
import com.example.demo.repository.RoomFeatureRepository;
import com.example.demo.repository.RoomRepository;

@Service
public class RoomFeatureMappingService {
    private final RoomFeatureMappingRepository roomFeatureMappingRepository;
    private final RoomFeatureRepository roomFeatureRepository;
    private final RoomRepository roomRepository;

    @Autowired
    public RoomFeatureMappingService(RoomFeatureMappingRepository roomFeatureMappingRepository,
            RoomFeatureRepository roomFeatureRepository, RoomRepository roomRepository) {
        this.roomFeatureMappingRepository = roomFeatureMappingRepository;
        this.roomFeatureRepository = roomFeatureRepository;
        this.roomRepository = roomRepository;
    }
    
    public List<RoomFeatureMappingDTO> getAll(){
        return roomFeatureMappingRepository.getAll();
    }

    public RoomFeatureMappingDTO get(Integer id){
        return roomFeatureMappingRepository.get(id);
    }

    public Boolean save(RoomFeatureMappingDTO roomdto){
        RoomFeatureMapping roomFeatureMapping = new RoomFeatureMapping();

        Room room = roomRepository.findById(roomdto.getRoomId()).orElse(null);
        if (room == null) {
            return false;
        }
        roomFeatureMapping.setRoom(room);

        RoomFeature roomFeature = roomFeatureRepository.findById(roomdto.getRoomId()).orElse(null);
        if (roomFeature == null) {
            return false;
        }
        roomFeatureMapping.setRoomFeature(null);

        roomFeatureMapping.setQuantity(roomdto.getQuantity());
        roomFeatureMapping.setCreatedAt(LocalDateTime.now());
        roomFeatureMapping.setUpdatedAt(LocalDateTime.now());

        roomFeatureMappingRepository.save(roomFeatureMapping);

        return roomFeatureMappingRepository.findById(roomFeatureMapping.getId()).isPresent();
    }

    public Boolean update(Integer id, RoomFeatureMappingDTO roomdto){
        RoomFeatureMapping existingRoomFeatureMapping = roomFeatureMappingRepository.findById(id).orElse(null);
       
        existingRoomFeatureMapping.setQuantity(roomdto.getQuantity());
        existingRoomFeatureMapping.setUpdatedAt(LocalDateTime.now());

        roomFeatureMappingRepository.save(existingRoomFeatureMapping);

        return roomFeatureMappingRepository.findById(existingRoomFeatureMapping.getId()).isPresent();
    }
}
