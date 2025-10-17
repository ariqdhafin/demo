package com.example.demo.controllers.api;

import java.util.LinkedHashMap;
import java.util.Map;

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
        Map<String, Object> response = new LinkedHashMap<>();

        if (token == null || token.isEmpty()){
            response.put("status", "error");
            response.put("message", "Token tidak boleh kosong");
            return ResponseEntity.status(400).body(response);
        }

        if (!token.equals("abcd")) {
            response.put("status", "error");
            response.put("message", "Token tidak valid");
            return ResponseEntity.status(400).body(response);
        }

        RoleDTO roleDTO = roleService.get(id);

        if(roleDTO == null){
            response.put("status", "error");
            response.put("message", "Data tidak ditemukan");
            return ResponseEntity.status(400).body(response);
        }

        response.put("status", "success");
        response.put("message", "Data ditemukan");
        response.put("data", roleDTO);
        return ResponseEntity.status(200).body(response);
    }

    @PostMapping
    public ResponseEntity<?> save(
        @RequestHeader(name = "token") String token, 
        @RequestBody RoleDTO roleDTO) 
    {
        Map<String, Object> response = new LinkedHashMap<>();

        if (token == null || token.isEmpty()){
            response.put("status", "error");
            response.put("message", "Token tidak boleh kosong");
            return ResponseEntity.status(400).body(response);
        }

        if (!token.equals("abcd")) {
            response.put("status", "error");
            response.put("message", "Token tidak valid");
            return ResponseEntity.status(400).body(response);
        }

        Boolean success = roleService.save(roleDTO);
        if(success){
            response.put("status", "success");
            response.put("message", "Data berhasil disimpan");
            return ResponseEntity.status(200).body(response);
        }else{
            response.put("status", "error");
            response.put("message", "Data gagal disimpan");
            return ResponseEntity.status(400).body(response);
        }
        
    }

    @PutMapping
    public ResponseEntity<?> update(
        @RequestHeader(name = "token") String token,
        @RequestParam(name = "id") Integer id, 
        @RequestBody RoleDTO roleDTO)
    {
        Map<String, Object> response = new LinkedHashMap<>();
        
        if (token == null || token.isEmpty()){
            response.put("status", "error");
            response.put("message", "Token tidak boleh kosong");
            return ResponseEntity.status(400).body(response);
        }

        if (!token.equals("abcd")) {
            response.put("status", "error");
            response.put("message", "Token tidak valid");
            return ResponseEntity.status(400).body(response);
        }

        RoleDTO roleDTOById = roleService.get(id);

        if(roleDTOById  == null){
            response.put("status", "error");
            response.put("message", "Data tidak ditemukan");
            return ResponseEntity.status(400).body(response);
        }
        
        roleDTO.setId(id);
        Boolean success = roleService.save(roleDTO);

        if(success){
            response.put("status", "success");
            response.put("message", "Data berhasil disimpan");
            response.put("data", roleDTO);
            return ResponseEntity.status(200).body(response);
        }else{
            response.put("status", "error");
            response.put("message", "Data gagal disimpan");
            return ResponseEntity.status(400).body(response);
        }
    }

    // @DeleteMapping
    // public ResponseEntity
}
