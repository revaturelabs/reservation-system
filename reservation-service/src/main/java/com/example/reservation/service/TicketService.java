package com.example.reservation.service;

import com.example.reservation.model.Ticket;
import com.example.reservation.payloads.TicketCancelPayload;
import com.example.reservation.payloads.TicketPayload;
import org.bson.types.ObjectId;

import java.util.List;

public interface TicketService {

    TicketPayload bookNewTicket(TicketPayload ticketPayload);
    List<Ticket> getTickets();
    TicketCancelPayload cancelTicket(ObjectId ticketId);
    TicketCancelPayload cancelTicketForAPassenger(ObjectId ticketId, int seatNumber);
}
