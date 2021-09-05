package com.example.reservation.rest.payloads;

import com.example.reservation.model.Bus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class TripPayload {

    private String id;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime depTime;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime arrivalDateTime;
    private BusPayload bus;
    private int[] reservedSeats;
    private double price;

}
