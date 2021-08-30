package com.example.reservationservice.model;

import com.mongodb.internal.connection.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BusStopPoint {
    String name;
    int distance;
    Time time;
}
