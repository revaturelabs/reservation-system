package com.example.reservation.repository;

import com.example.reservation.model.Bus;
import com.example.reservation.model.BusType;
import com.example.reservation.model.SeatType;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends MongoRepository<Bus, String> {
  List<Bus> findByType(BusType busType);
  List<Bus> findBySeatType(SeatType seatType);
  List<Bus> findByName(String name);
}
