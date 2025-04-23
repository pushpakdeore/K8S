package com.example.user.service.dto;

import jakarta.validation.constraints.NegativeOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NegativeOrZero
@AllArgsConstructor
public class LoginDTO {

    private String email;
    private String password;
}
