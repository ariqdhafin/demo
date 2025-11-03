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
import com.example.demo.Models.dto.RoomFeatureMappingDTO;
import com.example.demo.services.RoomFeatureMappingService;

@RestController
@RequestMapping("api/roomFeatureMapping")
public class RoomFeatureMappingAPI {
    private final RoomFeatureMappingService roomFeatureMappingService;

    @Autowired
    public RoomFeatureMappingAPI(RoomFeatureMappingService roomFeatureMappingService){
        this.roomFeatureMappingService = roomFeatureMappingService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
    ){
        List<RoomFeatureMappingDTO> roomFeatureMappingDTO = roomFeatureMappingService.getAll();

        if(roomFeatureMappingDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",roomFeatureMappingDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
        @PathVariable Integer id
    )
    {
        RoomFeatureMappingDTO roomFeatureMappingDTO = roomFeatureMappingService.get(id);

        if(roomFeatureMappingDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",roomFeatureMappingDTO));
    }

    @PostMapping
    public ResponseEntity<?> insert(
        @RequestBody RoomFeatureMappingDTO roomFeatureMappingDTO
    ){
        Boolean success = roomFeatureMappingService.save(roomFeatureMappingDTO);

         if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil disimpan",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal disimpan",null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id,
        @RequestBody RoomFeatureMappingDTO roomFeatureMappingDTO
    ){
        RoomFeatureMappingDTO roomFeatureMappingDTOById = roomFeatureMappingService.get(id);

        if(roomFeatureMappingDTOById == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        Boolean success = roomFeatureMappingService.update(id, roomFeatureMappingDTO);

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
    //     RoomFeatureMappingDTO roomFeatureMappingDTOById = roomFeatureMappingService.get(id);

    //     if(roomFeatureMappingDTOById == null){
    //         return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
    //     }

    //     Boolean success = roomFeatureMappingService.remove(id);

    //     if(success){
    //         return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil dihapus",null));
    //     }else{
    //         return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal dihapus",null));
    //     }
    // }
}
