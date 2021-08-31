package com.example.reservation.repository;

import com.example.reservation.model.Bus;
import com.example.reservation.model.BusType;
import com.example.reservation.model.SeatType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class BusRepositoryTest {

    @Autowired
    private BusRepository busRepository;

    @BeforeEach
    public void beforeEach(){

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

    }

    @Test
    public void getAll(){
       List<Bus> busList= busRepository.findAll();
       assertEquals(2,busList.size());
    }

    @Test
    public void getBusByNumber(){
        Bus bus= busRepository.findById("TN18BZ1108").get();
        assertEquals("TN18BZ1108",bus.getNumber());
    }

    @Test
    public void getAcBus(){
       List<Bus> busList= busRepository.findByType(BusType.AC);
        assertEquals(2,busList.size());
    }


    @Test
    public void getNonAcBus(){
        List<Bus> busList= busRepository.findByType(BusType.NON_AC);
        assertEquals(0,busList.size());
    }


    @AfterEach
    public void afterEach(){
        busRepository.deleteAll();
    }


}
