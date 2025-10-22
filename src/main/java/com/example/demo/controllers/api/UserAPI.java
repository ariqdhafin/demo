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

import com.example.demo.Models.dto.UserDTO;
import com.example.demo.Models.dto.ResponseDTO;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("api/user")
public class UserAPI {
    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<UserDTO> userDTO = userService.getAll();

        if(userDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",userDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
        @PathVariable Integer id
    )
    {
        UserDTO userDTO = userService.get(id);

        if(userDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",userDTO));
    }

    @PostMapping
    public ResponseEntity<?> insert(
        @RequestBody UserDTO userDTO
    ){
        Boolean success = userService.save(userDTO);

        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil disimpan",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal disimpan",null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id,
        @RequestBody UserDTO userDTO
    ){
        UserDTO userDTOById = userService.get(id);

        if(userDTOById == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        userDTO.setId(id);

        Boolean success = userService.save(userDTO);

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
        UserDTO userDTOById = userService.get(id);

        if(userDTOById == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        Boolean success = userService.remove(id);

        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil dihapus",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal dihapus",null));
        }
    }
}
