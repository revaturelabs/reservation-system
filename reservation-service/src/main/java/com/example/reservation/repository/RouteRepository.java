package com.example.reservation.repository;

import com.example.reservation.model.Route;
import com.example.reservation.model.Trip;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RouteRepository extends MongoRepository<Route, String> {

    @Query("{'$and':[ {'source':?0}, {'destination':?1} ] }")
    Route find(String source, String destination, LocalDate localDate);


}
