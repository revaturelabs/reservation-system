package com.example.reservation.repository;

import com.example.reservation.model.Route;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteRepository extends MongoRepository<Route, ObjectId> {
}
