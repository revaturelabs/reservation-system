package com.example.reservation.service;

import com.example.reservation.rest.payloads.TicketPayload;

public interface TicketService {

    TicketPayload bookNewTicket(TicketPayload ticketPayload);

}
