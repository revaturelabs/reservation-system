package com.example.reservation.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class TripRequestPayload {
    private String id;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime depTime;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime arrivalDateTime;
}
