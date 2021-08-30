package com.example.reservation.repository;

import com.example.reservation.model.Bus;
import com.example.reservation.model.BusType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRepository extends MongoRepository<Bus,String> {

//    List<Bus> findByType(BusType type);

    // or

    @Query("{'type':?0}")
    List<Bus> findAll(BusType type);

}
