package com.example.reservationservice.model;

import com.mongodb.internal.connection.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class BusStopPoint {
    private String name;
    private int distance;
    private Time time;

}
