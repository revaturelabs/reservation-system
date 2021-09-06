package com.example.reservation.rest;

import com.example.reservation.model.Ticket;
import com.example.reservation.payloads.TicketRequestPayload;
import com.example.reservation.payloads.TicketResponsePayload;
import com.example.reservation.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "tickets", description ="REST API for ticket resource")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> bookNewTicket(@RequestBody TicketRequestPayload ticketRequestPayload){
        SecurityContext context = SecurityContextHolder.getContext();
        String userEmail = context.getAuthentication().getName();
        ticketRequestPayload.setUserEmail(userEmail);

        TicketResponsePayload ticketResponsePayload= ticketService.bookNewTicket(ticketRequestPayload);
        return  ResponseEntity.status(HttpStatus.CREATED).body(ticketResponsePayload);
    }

    @GetMapping
    public ResponseEntity<?> getTickets(){
        List<Ticket> tickets= ticketService.getTickets();
        return  ResponseEntity.status(HttpStatus.CREATED).body(tickets);
    }


}
