package com.example.reservation.service;

import com.example.reservation.model.Route;
import com.example.reservation.model.Trip;

public interface RouteService {
    Route addNewRoute(Route route);
    Trip addNewTrip(String routeId,Trip trip);
    void addBusToRouteTrip(String routeId,String tripId,String busNumber);
    Route getRoute(String source,String destination);
}
