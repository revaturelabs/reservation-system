package com.example.reservationservice.repository;

import com.example.reservationservice.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus,String> {
}
