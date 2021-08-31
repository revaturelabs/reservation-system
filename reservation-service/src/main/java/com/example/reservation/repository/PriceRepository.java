package com.example.reservation.repository;

import com.example.reservation.model.BusType;
import com.example.reservation.model.PriceMatrix;
import com.example.reservation.model.SeatType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PriceRepository extends MongoRepository<PriceMatrix, ObjectId> {

    @Query("{'$and':[ {'busType':?0}, {'seatType':?1} ] }")
    PriceMatrix findPriceMatrix(BusType busType, SeatType seatType);

}
