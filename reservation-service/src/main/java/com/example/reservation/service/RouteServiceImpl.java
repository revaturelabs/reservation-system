package com.example.reservation.service;

import com.example.reservation.exception.ResourceNotFoundException;
import com.example.reservation.model.Bus;
import com.example.reservation.model.Route;
import com.example.reservation.model.Trip;
import com.example.reservation.repository.BusRepository;
import com.example.reservation.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteServiceImpl implements RouteService{

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;

//    @Autowired
//    private MongoTemplate mongoTemplate;

    @Override
    public Route addNewRoute(Route route) {
       return routeRepository.insert(route);
    }

    @Override
    public Trip addNewTrip(String routeId, Trip trip) {
        Optional<Route> optionalRoute =routeRepository.findById(routeId);
        Route route=optionalRoute.orElseThrow(()->new ResourceNotFoundException(routeId));
        route.getTripList().add(trip);
        Route updatedRoute= routeRepository.save(route);
        return  trip;
    }

    @Override
    public void addBusToRouteTrip(String routeId, String tripId, String busNumber) {

        Optional<Route> optionalRoute =routeRepository.findById(routeId);
        Route route=optionalRoute.orElseThrow(()->new ResourceNotFoundException(routeId));

        System.out.println(route.getTripList().size());

        Optional<Trip> optionalTrip=route.getTripList().stream().filter(t->t.getId().equals(tripId)).findFirst();
        Trip trip= optionalTrip.orElseThrow();

        Optional<Bus> optionalBus=busRepository.findById(busNumber);
        Bus bus=optionalBus.orElseThrow();

        bus.setRoute(route);
        trip.setBus(bus);

        busRepository.save(bus);
        routeRepository.save(route);

    }

    @Override
    public Route getRoute(String source, String destination) {
        return routeRepository.find(source,destination);
    }
}
