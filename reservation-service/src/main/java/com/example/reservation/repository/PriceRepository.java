package com.example.reservation.repository;

import com.example.reservation.model.PriceMatrix;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceRepository extends MongoRepository<PriceMatrix, ObjectId> {
}
