package com.example.reservation.repository;

import com.example.reservation.model.Ticket;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, ObjectId> {

    @Query("{'user.email':?0}")
    List<Ticket> findAll(String userEmail);

}
