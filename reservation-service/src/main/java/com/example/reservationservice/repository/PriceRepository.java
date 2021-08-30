package com.example.reservationservice.repository;

import com.example.reservationservice.model.PriceMatrix;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<PriceMatrix, ObjectId> {
}
