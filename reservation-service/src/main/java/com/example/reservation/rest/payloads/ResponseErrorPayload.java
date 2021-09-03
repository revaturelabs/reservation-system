package com.example.reservation.rest.payloads;

import lombok.Data;

@Data
public class ResponseErrorPayload {
    private boolean error;
    private String message;
}
