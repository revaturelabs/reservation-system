package com.example.reservation.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class AuthResponse {
    private String messsage;
    private final String jwt;
}
