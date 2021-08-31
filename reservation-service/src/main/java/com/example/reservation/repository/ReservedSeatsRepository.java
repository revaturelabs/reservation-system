package com.example.reservation.repository;

import com.example.reservation.model.ReservedSeats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReservedSeatsRepository extends MongoRepository<ReservedSeats, ObjectId> {
}
