package com.example.demo.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.dto.ResponseDTO;
import com.example.demo.Models.dto.RoleDTO;
import com.example.demo.services.RoleService;

@RestController
@RequestMapping("api/role")
public class RoleAPI {
    private final RoleService roleService;

    @Autowired
    public RoleAPI(RoleService roleService){
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<?> get(
        @RequestHeader(name = "token") String token, 
        @RequestParam(name = "id") Integer id)
    {

        if (token == null || token.isEmpty()){
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak boleh kosong",null));
        }

        if (!token.equals("abcd")) {
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak valid",null));
        }

        RoleDTO roleDTO = roleService.get(id);

        if(roleDTO == null){
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",roleDTO));
    }

    // @PostMapping
    // public ResponseEntity<?> insert(
    //     @RequestHeader(name = "token") String token, 
    //     @RequestBody RoleDTO roleDTO) 
    // {
    //     if (token == null || token.isEmpty()){
    //         return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak boleh kosong",null));
    //     }

    //     if (!token.equals("abcd")) {
    //         return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak valid",null));
    //     }

    //     Boolean success = roleService.save(roleDTO);
    //     if(success){
    //         return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil disimpan",null));
    //     }else{
    //         return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal disimpan",null));
    //     }
        
    // }

    @PostMapping
    public ResponseEntity<?> insertUpdate(
        @RequestHeader(name = "token") String token,
        @RequestParam(name = "id", required = false) Integer id, 
        @RequestBody RoleDTO roleDTO)
    {
        if (token == null || token.isEmpty()){
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak boleh kosong",null));
        }

        if (!token.equals("abcd")) {
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak valid",null));
        }

        if (id != null) {
            RoleDTO roleDTOById = roleService.get(id);

            if(roleDTOById == null){
                return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
            }
            
            roleDTO.setId(id);
        }
        
        Boolean success = roleService.save(roleDTO);

        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil disimpan",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal disimpan",null));
        }
        
    }

    @DeleteMapping
    public ResponseEntity<?> delete(
        @RequestHeader(name = "token") String token, 
        @RequestParam(name = "id") Integer id)
    {
        
        if (token == null || token.isEmpty()){
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak boleh kosong",null));
        }

        if (!token.equals("abcd")) {
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Token tidak valid",null));
        }

        RoleDTO roleDTOById = roleService.get(id);

        if(roleDTOById == null){
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        Boolean success = roleService.remove(id);
        if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil dihapus",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal dihapus",null));
        }
    }
}
