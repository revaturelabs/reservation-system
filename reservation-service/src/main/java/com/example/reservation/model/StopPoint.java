package com.example.reservation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class StopPoint {
    private String name;
    private int distanceFromSource;
}
