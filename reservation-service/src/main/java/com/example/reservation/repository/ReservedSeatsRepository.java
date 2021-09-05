package com.example.reservation.repository;

import com.example.reservation.model.ReservedSeats;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReservedSeatsRepository extends MongoRepository<ReservedSeats, ObjectId> {

    @Query("{'$and':[ {'travelDate':?0}, {'bus.number':?1} ] }")
    ReservedSeats findReservedSeats(LocalDate travelDate,String busNumber);

}
