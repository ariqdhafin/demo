package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Reservation;
import com.example.demo.Models.Room;
import com.example.demo.Models.dto.FeatureDTO;
import com.example.demo.Models.dto.RoomDTO;
import com.example.demo.Models.dto.RoomFeatureDTO;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.RoomFeatureMappingRepository;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
    private final RoomFeatureMappingRepository roomFeatureMappingRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, ReservationRepository reservationRepository,
            RoomFeatureMappingRepository roomFeatureMappingRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
        this.roomFeatureMappingRepository = roomFeatureMappingRepository;
    }

    public List<RoomDTO> getAll(){
        List<RoomDTO> roomDTOs =  roomRepository.getAll();

        for(RoomDTO roomDTO: roomDTOs){
            List<FeatureDTO> featureDTOs = roomFeatureMappingRepository.findByRoomId(roomDTO.getId())
                .stream()
                .map(x -> new FeatureDTO(
                    x.getRoomFeature().getId(),
                    x.getRoomFeature().getName(),
                    x.getRoomFeature().getDescription(),
                    x.getQuantity()
                ))
                .collect(Collectors.toList());
            roomDTO.setFeature(featureDTOs);
        }

        return roomDTOs;
    }

    public RoomDTO get(Integer id){
        RoomDTO roomDTO = roomRepository.get(id);

        List<FeatureDTO> featureDTOs = roomFeatureMappingRepository.findByRoomId(roomDTO.getId())
            .stream()
            .map(x -> new FeatureDTO(
                x.getRoomFeature().getId(),
                x.getRoomFeature().getName(),
                x.getRoomFeature().getDescription(),
                x.getQuantity()
            ))
            .collect(Collectors.toList());
        roomDTO.setFeature(featureDTOs);
        return roomDTO;
    }

    public Boolean save(RoomDTO roomdto){
        Room room = new Room();
        room.setName(roomdto.getName());
        room.setLocation(roomdto.getLocation());
        room.setCapacity(roomdto.getCapacity());
        room.setStatus(roomdto.getStatus());
        room.setImageUrl(roomdto.getImageUrl());
        room.setCreatedAt(LocalDateTime.now());
        room.setUpdatedAt(LocalDateTime.now());

        roomRepository.save(room);

        return roomRepository.findById(room.getId()).isPresent();
    }

    public Boolean update(Integer id, RoomDTO roomdto){
        Room existingRoom = roomRepository.findById(id).orElse(null);
        existingRoom.setName(roomdto.getName());
        existingRoom.setLocation(roomdto.getLocation());
        existingRoom.setCapacity(roomdto.getCapacity());
        existingRoom.setStatus(roomdto.getStatus());
        existingRoom.setImageUrl(roomdto.getImageUrl());
        existingRoom.setUpdatedAt(LocalDateTime.now());

        roomRepository.save(existingRoom);

        return roomRepository.findById(existingRoom.getId()).isPresent();
    }

    public Boolean remove(Integer id){
        Room room = roomRepository.findById(id).orElse(null);
        if (room != null){
            List<Reservation> listReservations = room.getReservations();
            for(Reservation reservation: listReservations){
                reservation.setRoom(null);
                reservationRepository.save(reservation);
            }
        }
        roomRepository.deleteById(id);

        return !roomRepository.findById(id).isPresent();
    }
}
