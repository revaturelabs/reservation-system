package com.example.reservation.repository;

import com.example.reservation.model.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus,String> {

}
