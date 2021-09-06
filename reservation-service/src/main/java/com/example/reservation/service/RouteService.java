package com.example.reservation.service;

import com.example.reservation.model.Route;
import com.example.reservation.model.Trip;
import com.example.reservation.payloads.RoutePayload;
import com.example.reservation.payloads.TripRequestPayload;

import java.time.LocalDate;

public interface RouteService {
    Route addNewRoute(Route route);
    Trip addNewTrip(String routeId, TripRequestPayload trip);
    void addBusToRouteTrip(String routeId,String tripId,String busNumber);
    RoutePayload getRoute(LocalDate date,String source, String destination);
}
