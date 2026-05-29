package com.diego.hospital.hospital_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diego.hospital.hospital_api.dto.LoginDTO;
import com.diego.hospital.hospital_api.dto.RegisterDTO;
import com.diego.hospital.hospital_api.model.user.User;
import com.diego.hospital.hospital_api.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public User registrar(@Valid @RequestBody RegisterDTO dto){
        return authService.registrar(dto);

    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDTO dto){
        return authService.login(dto);
    }
    

}
