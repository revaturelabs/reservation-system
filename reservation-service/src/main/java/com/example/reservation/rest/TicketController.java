package com.example.reservation.rest;

import com.example.reservation.rest.payloads.TicketPayload;
import com.example.reservation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public void bookNewTicket(@RequestBody TicketPayload ticketPayload){
        ticketService.bookNewTicket(ticketPayload);
    }

}
