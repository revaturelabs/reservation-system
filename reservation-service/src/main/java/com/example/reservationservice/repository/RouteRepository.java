package com.example.reservationservice.repository;

import com.example.reservationservice.model.Route;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteRepository extends MongoRepository<Route, ObjectId> {
}
