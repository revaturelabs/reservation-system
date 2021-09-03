package com.example.reservation.rest.payload;

import lombok.Data;

@Data
public class ResponseError {
    private boolean error;
    private String message;
}
