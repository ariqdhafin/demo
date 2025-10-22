package com.example.demo.services;

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
        room.setId(roomdto.getId());
        room.setName(roomdto.getName());
        room.setCapacity(roomdto.getCapacity());
        room.setLocation(roomdto.getLocation());
        room.setStatus(roomdto.getStatus());

        roomRepository.save(room);

        return roomRepository.findById(room.getId()).isPresent();
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
