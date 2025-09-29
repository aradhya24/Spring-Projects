package com.aurionpro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.dto.UserResponse;
import com.aurionpro.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/superadmin")
@RequiredArgsConstructor
public class SuperAdminController {

    private final AuthService authService;

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @GetMapping("/admins")
    public ResponseEntity<List<UserResponse>> getAllAdmins() {
        return ResponseEntity.ok(authService.getAllAdmins());
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PatchMapping("/admins/{id}/inactivate")
    public ResponseEntity<String> inactivateAdmin(@PathVariable int id) {
        return ResponseEntity.ok(authService.inactivateAdmin(id));
    }

    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PatchMapping("/admins/{id}/activate")
    public ResponseEntity<String> activateAdmin(@PathVariable int id) {
        return ResponseEntity.ok(authService.activateAdmin(id));
    }
}
