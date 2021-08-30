package com.project.bus_reservation.model;

import com.mongodb.internal.connection.Time;

import java.util.List;

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