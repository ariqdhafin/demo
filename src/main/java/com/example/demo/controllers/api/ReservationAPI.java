package com.example.demo.controllers.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.dto.ReservationDTO;
import com.example.demo.Models.dto.ReservationListDTO;
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
        List<ReservationListDTO> reservationListDTO = reservationService.getAll();

        if(reservationListDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",reservationListDTO));
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

    @GetMapping("/reservedBy/{id}")
    public ResponseEntity<?> getByReservedById(
        @PathVariable Integer id
    )
    {
        List<ReservationListDTO> reservationListDTO = reservationService.getByReservedById(id);

        if(reservationListDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",reservationListDTO));
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

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id,
        @RequestBody ReservationDTO reservationDTO
    ){
        ReservationDTO existingReservation = reservationService.get(id);
        if(existingReservation == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        Boolean success = reservationService.update(id, reservationDTO);

        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil diperbarui",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal diperbarui",null));
        }
    }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<?> delete(
    //     @PathVariable Integer id
    // ){
    //     ReservationDTO reservationDTOById = reservationService.get(id);

    //     if(reservationDTOById == null){
    //         return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
    //     }

    //     Boolean success = reservationService.remove(id);

    //     if(success){
    //         return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil dihapus",null));
    //     }else{
    //         return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal dihapus",null));
    //     }
    // }
}
