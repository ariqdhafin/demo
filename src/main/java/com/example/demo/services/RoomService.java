package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Reservation;
import com.example.demo.Models.Room;
import com.example.demo.Models.dto.RoomDTO;
import com.example.demo.repository.ReservationRepository;
import com.example.demo.repository.RoomRepository;

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomDTO> getAll(){
        return roomRepository.getAll();
    }

    public RoomDTO get(Integer id){
        return roomRepository.get(id);
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
