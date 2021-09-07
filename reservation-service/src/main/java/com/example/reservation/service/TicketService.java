package com.example.reservation.service;

import com.example.reservation.model.Ticket;
import com.example.reservation.payloads.TicketRefundPayload;
import com.example.reservation.payloads.TicketRequestPayload;
import com.example.reservation.payloads.TicketResponsePayload;
import org.bson.types.ObjectId;

import java.util.List;

public interface TicketService {

    TicketResponsePayload bookNewTicket(TicketRequestPayload ticketPayload);
    TicketRefundPayload cancelTicketForAPassenger(ObjectId ticketId, int seatNumber);
    TicketRefundPayload cancelTicket(ObjectId ticketId);
    List<Ticket> getUserTickets(String userEmail);
    List<Ticket> getAllTickets();

}
