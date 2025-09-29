package com.aurionpro.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class EmployeePatchDto {

    private String firstName;

    private String lastName;

    @Email
    private String email;
    

    
}
