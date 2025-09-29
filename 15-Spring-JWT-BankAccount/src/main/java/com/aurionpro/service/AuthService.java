package com.aurionpro.service;

import java.util.List;

import com.aurionpro.dto.LoginDto;
import com.aurionpro.dto.RegistrationDto;
import com.aurionpro.dto.UserResponse;

public interface AuthService {

    // Super Admin registration (Admin/Customer)
    UserResponse register(RegistrationDto registration);

    // Public Customer registration
    UserResponse publicRegister(RegistrationDto registerDto);

    // Login
    String login(LoginDto loginDto);

    // ---------------- SUPER_ADMIN METHODS ----------------
    List<UserResponse> getAllAdmins();
    String inactivateAdmin(int adminId);
    String activateAdmin(int adminId);
}
