package com.example.reservation.service;

import com.example.reservation.model.Ticket;
import com.example.reservation.payloads.TicketRequestPayload;
import com.example.reservation.payloads.TicketResponsePayload;

import java.util.List;

public interface TicketService {

    TicketResponsePayload bookNewTicket(TicketRequestPayload ticketPayload);
    List<Ticket> getTickets();

}
