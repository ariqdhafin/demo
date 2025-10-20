package com.example.demo.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.dto.ResponseDTO;
import com.example.demo.Models.dto.RoomDTO;
import com.example.demo.services.RoomService;

@RestController
@RequestMapping("api/room")
public class RoomAPI {
    private final RoomService roomService;

    @Autowired
    public RoomAPI(RoomService roomService){
        this.roomService = roomService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
        @RequestHeader(name = "token") String token
    ){
        if (token != null && !token.isEmpty()){
            if (!token.equals("abcd")) {
                return ResponseEntity.status(401).body(new ResponseDTO<>("error","Token tidak valid",null));
            }
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak boleh kosong",null));
        }

        List<RoomDTO> roomDTO = roomService.getAll();

        if(roomDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",roomDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
        @RequestHeader(name = "token") String token,
        @PathVariable Integer id
    )
    {
        if (token != null && !token.isEmpty()){
            if (!token.equals("abcd")) {
                return ResponseEntity.status(401).body(new ResponseDTO<>("error","Token tidak valid",null));
            }
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak boleh kosong",null));
        }

        RoomDTO roomDTO = roomService.get(id);

        if(roomDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",roomDTO));
    }

    @PostMapping
    public ResponseEntity<?> insert(
        @RequestHeader(name = "token") String token,
        @RequestBody RoomDTO roomDTO
    ){
        if (token != null && !token.isEmpty()){
            if (!token.equals("abcd")) {
                return ResponseEntity.status(401).body(new ResponseDTO<>("error","Token tidak valid",null));
            }
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak boleh kosong",null));
        }

        Boolean success = roomService.save(roomDTO);

         if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil disimpan",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal disimpan",null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @RequestHeader(name = "token") String token,
        @PathVariable Integer id,
        @RequestBody RoomDTO roomDTO
    ){
        if (token != null && !token.isEmpty()){
            if (!token.equals("abcd")) {
                return ResponseEntity.status(401).body(new ResponseDTO<>("error","Token tidak valid",null));
            }
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak boleh kosong",null));
        }

        RoomDTO roomDTOById = roomService.get(id);

        if(roomDTOById == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        roomDTO.setId(id);

        Boolean success = roomService.save(roomDTO);

        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil diperbarui",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal diperbarui",null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
        @RequestHeader(name = "token") String token,
        @PathVariable Integer id
    ){
        if (token != null && !token.isEmpty()){
            if (!token.equals("abcd")) {
                return ResponseEntity.status(401).body(new ResponseDTO<>("error","Token tidak valid",null));
            }
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak boleh kosong",null));
        }

        RoomDTO roomDTOById = roomService.get(id);

        if(roomDTOById == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        Boolean success = roomService.remove(id);

        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil dihapus",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal dihapus",null));
        }
    }
}
