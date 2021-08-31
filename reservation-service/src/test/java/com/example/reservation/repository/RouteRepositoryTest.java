package com.example.reservation.repository;

import com.example.reservation.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataMongoTest
@ExtendWith(SpringExtension.class)
public class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;

    @BeforeEach
    public void beforeEach(){
        Route route=new Route();
        route.setSource("CHENNAI");
        route.setDestination("BENGALURU");
        route.setDistance(330);
        route.setDepTime(LocalTime.of(20,30));
        route.setArrivalTime(LocalTime.of(2,30));
        List<StopPoint> stopPoints=List.of(
                new StopPoint("point-1",150),
                new StopPoint("point-2",200)
        );
        route.setStopPoints(stopPoints);
        routeRepository.save(route);
    }

    @Test
    public void addNewRoute(){
       List<Route> routes= routeRepository.findAll();
       assertEquals(1,routes.size());
    }


    @Test
    public void getRouteBySourceAndDestinations(){
        Route route=routeRepository.find("CHENNAI","BENGALURU");
        assertNotNull(route);
    }

    @Test
    public void addNewStopPoint(){
        Route route=routeRepository.find("CHENNAI","BENGALURU");
        route.getStopPoints().add(new StopPoint("point-3",280));
        routeRepository.save(route);

         route=routeRepository.find("CHENNAI","BENGALURU");
         assertEquals(3,route.getStopPoints().size());

    }

    @Test
    public void addNewBusToRoute(){

        Route route=routeRepository.find("CHENNAI","BENGALURU");

        Bus bus1=new Bus();
        bus1.setNumber("TN18BZ1109");
        bus1.setName("Royal Bus");
        bus1.setSeatType(SeatType.CHAIR);
        bus1.setType(BusType.AC);
        bus1.setSeats(40);

        Bus bus2=new Bus();
        bus2.setNumber("TN18BZ1108");
        bus2.setName("Classic Bus");
        bus2.setType(BusType.AC);
        bus2.setSeatType(SeatType.SLEEPER);
        bus2.setSeats(20);

        busRepository.save(bus1);
        busRepository.save(bus2);

        route.getBusList().add(bus1);
        route.getBusList().add(bus2);

        routeRepository.save(route);

        route=routeRepository.find("CHENNAI","BENGALURU");

        assertEquals(2,route.getBusList().size());

    }

}
