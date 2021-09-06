package com.example.reservation.rest;

import com.example.reservation.model.Route;
import com.example.reservation.model.Trip;
import com.example.reservation.payloads.RouteResponsePayload;
import com.example.reservation.payloads.TripRequestPayload;
import com.example.reservation.service.RouteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Tag(name = "routes", description ="REST API for route resource")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    RouteService routeService;


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");


    @GetMapping("/{source}/{destination}/{travelDate}")
    public ResponseEntity<?> get(
            @PathVariable("source") String source,
            @PathVariable("destination") String destination,
            @PathVariable("travelDate") String travelDate
    ){
        LocalDate localDate=LocalDate.parse(travelDate,formatter);
        System.out.println(localDate);
        RouteResponsePayload route=routeService.getRoute(localDate,source,destination);
        return  ResponseEntity.status(HttpStatus.OK).body(route);
    }


    @PostMapping
    public ResponseEntity<?> addNewRoute(@RequestBody Route route){
       Route savedRoute= routeService.addNewRoute(route);
       return ResponseEntity.status(HttpStatus.CREATED).body(savedRoute);
    }

    @PutMapping("/{routeId}/trip")
    public ResponseEntity<?> addNewTripToRoute(@PathVariable("routeId") String routeId, @RequestBody TripRequestPayload trip){
        Trip savedTrip=routeService.addNewTrip(routeId,trip);
        return ResponseEntity.status(HttpStatus.OK).body(trip);
    }

    @PutMapping("/{routeId}/trip/{tripId}/bus/{busNumber}")
    public ResponseEntity<?> addBusToRouteTrip(
            @PathVariable("routeId") String routeId,
            @PathVariable("tripId") String tripId,
            @PathVariable("busNumber") String busNumber
            ){

        routeService.addBusToRouteTrip(routeId,tripId,busNumber);

        return  ResponseEntity.status(HttpStatus.OK).build();

    }



}
