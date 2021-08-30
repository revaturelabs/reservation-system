package com.example.reservationservice.repository;

import com.example.reservationservice.model.Ticket;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, ObjectId> {
}
