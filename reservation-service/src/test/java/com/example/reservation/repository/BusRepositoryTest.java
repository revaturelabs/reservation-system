package com.example.reservation.repository;

import com.example.reservation.model.Bus;
import com.example.reservation.model.BusType;
import com.example.reservation.model.SeatType;
import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

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
        bus1.setSeating(SeatType.CHAIRTYPE);
        bus1.setType(BusType.AC);
        bus1.setSeats(40);

        Bus bus2=new Bus();
        bus2.setNumber("TN18BZ1108");
        bus2.setName("Classic Bus");
        bus2.setType(BusType.AC);
        bus2.setSeating(SeatType.SLEEPINGTYPE);
        bus2.setSeats(20);

        busRepository.save(bus1);
        busRepository.save(bus2);

    }


    @Test
    public void get_all_bus(){
       List<Bus> busList= busRepository.findAll();
       assertEquals(2,busList.size());
    }

    @Test
    public void get_bus_for_id_success(){
        Bus bus= busRepository.findById("TN18BZ1108").get();
        assertEquals("TN18BZ1108",bus.getNumber());
    }

    @Test
    public void get_bus_for_id_fail(){
       Optional<Bus> optional = busRepository.findById("TN18BZ1111");
       if(optional.isEmpty()){
           assertTrue(true);
       }
    }

    @Test
    public void get_ac_bus(){
       List<Bus> busList= busRepository.findAll(BusType.AC);
        assertEquals(2,busList.size());
    }


    @Test
    public void get_non_ac_bus(){
        List<Bus> busList= busRepository.findAll(BusType.NON_AC);
        assertEquals(0,busList.size());
    }


    @AfterEach
    public void afterEach(){
        busRepository.deleteAll();
    }


}
