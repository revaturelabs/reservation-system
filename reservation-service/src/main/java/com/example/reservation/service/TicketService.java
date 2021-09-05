package com.example.reservation.service;

import com.example.reservation.model.Ticket;
import com.example.reservation.rest.payloads.TicketPayload;

import java.util.List;

public interface TicketService {

    TicketPayload bookNewTicket(TicketPayload ticketPayload);
    List<Ticket> getTickets();

}
