package com.example.reservation.repository;

import com.example.reservation.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
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
        routeRepository.save(route);
    }

    @Test
    public void addNewRoute(){
       List<Route> routes= routeRepository.findAll();
       assertEquals(1,routes.size());
    }


    @Test
    public void getRouteBySourceAndDestinations(){
        Route route=routeRepository.find("CHENNAI","BENGALURU", LocalDate.now());
        assertNotNull(route);
    }


    @AfterEach
    public void afterEach(){
        routeRepository.deleteAll();
    }

}
