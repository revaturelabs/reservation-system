package com.example.reservation.payloads;

import lombok.Data;

@Data
public class ResponseErrorPayload {
    private boolean error;
    private String message;
}
