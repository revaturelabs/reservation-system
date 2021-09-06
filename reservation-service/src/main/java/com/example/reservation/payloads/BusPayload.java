package com.example.reservation.payloads;

import com.example.reservation.model.BusContact;
import com.example.reservation.model.BusType;
import com.example.reservation.model.SeatType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class BusPayload {

    private String number;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BusType type;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private SeatType seatType;
    private int seats;
    private BusContact contact;
}
