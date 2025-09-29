package com.aurionpro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dto.JwtAuthResponse;
import com.aurionpro.dto.LoginDto;
import com.aurionpro.dto.RegistrationDto;
import com.aurionpro.dto.UserResponse;
import com.aurionpro.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegistrationDto registerDto) {
        return ResponseEntity.ok(authService.register(registerDto)); // Super Admin creates Admin/Customer
    }

    @PostMapping("/public-register")
    public ResponseEntity<UserResponse> publicRegister(@Valid @RequestBody RegistrationDto registerDto) {
        return ResponseEntity.ok(authService.publicRegister(registerDto)); // Anyone can create Customer only
    }


    /**
     * Public login endpoint
     */
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@Valid @RequestBody LoginDto loginDto) {
        String token = authService.login(loginDto);
        return ResponseEntity.ok(new JwtAuthResponse(token, "Bearer"));
    }
}
