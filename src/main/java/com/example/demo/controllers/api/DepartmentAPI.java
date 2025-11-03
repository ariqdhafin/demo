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
import com.example.demo.Models.dto.DepartmentDTO;
import com.example.demo.services.DepartmentService;

@RestController
@RequestMapping("api/department")
public class DepartmentAPI {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentAPI(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(
    ){
        List<DepartmentDTO> departmentDTO = departmentService.getAll();

        if(departmentDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",departmentDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(
        @PathVariable Integer id
    )
    {
        DepartmentDTO departmentDTO = departmentService.get(id);

        if(departmentDTO == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data ditemukan",departmentDTO));
    }

    @PostMapping
    public ResponseEntity<?> insert(
        @RequestBody DepartmentDTO departmentDTO
    ){
        Boolean success = departmentService.save(departmentDTO);

         if(success){
            return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil disimpan",null));
        }else{
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal disimpan",null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
        @PathVariable Integer id,
        @RequestBody DepartmentDTO departmentDTO
    ){
        DepartmentDTO departmentDTOById = departmentService.get(id);

        if(departmentDTOById == null){
            return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
        }

        Boolean success = departmentService.update(id, departmentDTO);

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
    //     DepartmentDTO departmentDTOById = departmentService.get(id);

    //     if(departmentDTOById == null){
    //         return ResponseEntity.status(404).body(new ResponseDTO<>("error","Data tidak ditemukan",null));
    //     }

    //     Boolean success = departmentService.remove(id);

    //     if(success){
    //         return ResponseEntity.status(200).body(new ResponseDTO<>("success","Data berhasil dihapus",null));
    //     }else{
    //         return ResponseEntity.status(400).body(new ResponseDTO<>("error","Data gagal dihapus",null));
    //     }
    // }
}
