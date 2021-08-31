package com.example.reservation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Bus {

    private String name;
    @Id
    private String number;
    private BusType type;
    private SeatType seatType;
    private int seats;
    @DBRef
    private Route route;
    private BusContact contact;

}
