package com.example.reservation.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.reservation.model.Bus;
import com.example.reservation.model.BusType;
import com.example.reservation.model.SeatType;

@Repository
public interface BusRepository extends MongoRepository<Bus,String> {

	@Query("{'name':?0}")
	Bus findByName(String name);
	
	@Query("{'type':?0}")
	Bus findByType(BusType type);
	
	@Query("{'seating':?0}")
	Bus findBySeat(SeatType seat);
	
	@Query("{'route.id':?0}")
	Bus findByRoute(ObjectId id);
}
