package com.example.reservationservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.reservationservice.model.Bus;
import com.example.reservationservice.model.BusContactPerson;
import com.example.reservationservice.model.BusType;
import com.example.reservationservice.model.Route;
import com.example.reservationservice.model.SeatType;

public interface BusRepository extends MongoRepository<Bus, String>{

	void add(String name, String number, BusType type, SeatType seating, int seats, Route route_id, BusContactPerson contact);
	
	void remove(String number);

	void updateByName(String name);
	
	void updateByBusType(BusType type);
	
	void updateBySeatType(SeatType seating);
	
	Bus findByNumber(String number);
	
	List<Bus> findByBusType(BusType type);
	
	List<Bus> findBySeatType(SeatType seating);

}
