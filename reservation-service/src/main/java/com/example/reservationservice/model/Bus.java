package com.example.reservationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bus {


    String name;
    String number;
    BusType type;
    SeatType seating;
    int seats;
    Route route_id;
    BusContactPerson contact;


}
