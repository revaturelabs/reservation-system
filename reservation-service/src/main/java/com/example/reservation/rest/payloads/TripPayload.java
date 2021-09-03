package com.example.reservation.rest.payloads;

import com.example.reservation.model.Bus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class TripPayload {

    private String id;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime depTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;
    private BusPayload bus;
    private List<Integer> reservedSeats;
    private double price;

}
