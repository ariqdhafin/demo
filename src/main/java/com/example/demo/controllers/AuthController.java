package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Models.dto.ResponseDTO;
import com.example.demo.Models.dto.UserDTO;
import com.example.demo.security.JwtUtil;
import com.example.demo.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Autowired
    public AuthController(JwtUtil jwtUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody UserDTO userData
    ){
        String username = userData.getUsername();
        String password = userData.getPassword();

        UserDTO userDTO = userService.getByUsername(username);

        if(userDTO == null){
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Username ditemukan",null));
        }

        if(!password.equals(userDTO.getPassword())){
            return ResponseEntity.status(400).body(new ResponseDTO<>("error","Password salah",null));
        }

        String token = jwtUtil.generateToken(username);
        Map<String, String> body = Map.of("token", token);

        return ResponseEntity.status(200).body(new ResponseDTO<>("success","Login Berhasil",body));
    }

    // @PostMapping("/register")
    // public ResponseEntity<?> register(
    //     @RequestBody UserDTO userDTO
    // ){
    //     Boolean success = userService.save(userDTO);
    // }
}
