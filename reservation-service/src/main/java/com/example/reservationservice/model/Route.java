package com.example.reservationservice.model;

import com.mongodb.internal.connection.Time;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Route {

    int route_id;
    String source;
    String destination;
    int distance;
    Time dep_time;
    Time arrival_time;
    List<BusStopPoint> stop_points;
    List<Bus> bus_list;

}