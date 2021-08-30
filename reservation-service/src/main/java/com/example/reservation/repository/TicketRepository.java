package com.example.reservation.repository;

import com.example.reservation.model.Ticket;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TicketRepository extends MongoRepository<Ticket, ObjectId> {
}
