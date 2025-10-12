package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Models.Room;
import com.example.demo.Models.dto.RoomDTO;
import com.example.demo.repository.RoomRepository;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }

    public List<Room> get(){
        return roomRepository.findAll();
    }

    public Room get(Integer id){
        return roomRepository.findById(id).orElse(null);
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
        roomRepository.deleteById(id);

        return !roomRepository.findById(id).isPresent();
    }
}
