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
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime depTime;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime arrivalTime;
    private BusPayload bus;
    private int[] reservedSeats;
    private double price;

}
