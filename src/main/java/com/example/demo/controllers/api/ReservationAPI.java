package com.example.demo.controllers.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.dto.ReservationDTO;
import com.example.demo.Models.dto.ResponseDTO;
import com.example.demo.services.ReservationService;

@RestController
@RequestMapping("api/reservation")
public class ReservationAPI {
    private final ReservationService reservationService;

    public ReservationAPI(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<ReservationDTO> reservationDTO = reservationService.getAll();

        if(reservationDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",reservationDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
        @PathVariable Integer id
    )
    {
        ReservationDTO reservationDTO = reservationService.get(id);

        if(reservationDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",reservationDTO));
    }

    @PostMapping
    public ResponseEntity<?> insert(
        @RequestBody ReservationDTO reservationDTO
    ){
        Boolean success = reservationService.save(reservationDTO);

        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil disimpan",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal disimpan",null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id,
        @RequestBody ReservationDTO reservationDTO
    ){
        ReservationDTO reservationDTOById = reservationService.get(id);

        if(reservationDTOById == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        reservationDTO.setId(id);

        Boolean success = reservationService.save(reservationDTO);

        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil diperbarui",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal diperbarui",null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
        @PathVariable Integer id
    ){
        ReservationDTO reservationDTOById = reservationService.get(id);

        if(reservationDTOById == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        Boolean success = reservationService.remove(id);

        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil dihapus",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal dihapus",null));
        }
    }
}
