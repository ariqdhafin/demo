package com.example.demo.controllers.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.dto.ResponseDTO;
import com.example.demo.Models.dto.RoomFeatureDTO;
import com.example.demo.services.RoomFeatureService;

@RestController
@RequestMapping("api/roomFeature")
public class RoomFeatureAPI {
    private final RoomFeatureService roomFeatureService;

    @Autowired
    public RoomFeatureAPI(RoomFeatureService roomFeatureService){
        this.roomFeatureService = roomFeatureService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
    ){
        List<RoomFeatureDTO> roomFeatureDTO = roomFeatureService.getAll();

        if(roomFeatureDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",roomFeatureDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
        @PathVariable Integer id
    )
    {
        RoomFeatureDTO roomFeatureDTO = roomFeatureService.get(id);

        if(roomFeatureDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",roomFeatureDTO));
    }

    @PostMapping
    public ResponseEntity<?> insert(
        @RequestBody RoomFeatureDTO roomFeatureDTO
    ){
        Boolean success = roomFeatureService.save(roomFeatureDTO);

         if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil disimpan",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal disimpan",null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id,
        @RequestBody RoomFeatureDTO roomFeatureDTO
    ){
        RoomFeatureDTO roomFeatureDTOById = roomFeatureService.get(id);

        if(roomFeatureDTOById == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        Boolean success = roomFeatureService.update(id, roomFeatureDTO);

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
    //     RoomFeatureDTO roomFeatureDTOById = roomFeatureService.get(id);

    //     if(roomFeatureDTOById == null){
    //         return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
    //     }

    //     Boolean success = roomFeatureService.remove(id);

    //     if(success){
    //         return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil dihapus",null));
    //     }else{
    //         return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal dihapus",null));
    //     }
    // }
}
