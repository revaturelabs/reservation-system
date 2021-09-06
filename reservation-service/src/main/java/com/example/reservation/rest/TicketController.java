package com.example.reservation.rest;

import com.example.reservation.model.Ticket;
import com.example.reservation.payloads.TicketCancelPayload;
import com.example.reservation.payloads.TicketPayload;
import com.example.reservation.service.TicketService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<?> bookNewTicket(@RequestBody TicketPayload ticketPayload){
        TicketPayload ticketResponsePayload= ticketService.bookNewTicket(ticketPayload);
        HttpHeaders headers=new HttpHeaders();
        headers.add("Location","http://localhost:8080/checkout");
        return  ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(ticketResponsePayload);
    }


    @GetMapping
    public ResponseEntity<?> getTickets(){
        List<Ticket> tickets= ticketService.getTickets();
        return  ResponseEntity.status(HttpStatus.CREATED).body(tickets);
    }

    @PutMapping("/{ticketId}")
    public ResponseEntity<TicketCancelPayload> cancelTicket(@PathVariable ObjectId ticketId){
       TicketCancelPayload refundAmt= ticketService.cancelTicket(ticketId);
        return ResponseEntity.status(HttpStatus.OK).body(refundAmt);
    }

    @PutMapping("/{ticketId}/{seatNumber}")
    public ResponseEntity<?> cancelTicketForAPassenger(@PathVariable ObjectId ticketId,@PathVariable int seatNumber){
       TicketCancelPayload refundAmt= ticketService.cancelTicketForAPassenger(ticketId,seatNumber);
        return ResponseEntity.status(HttpStatus.OK).body(refundAmt);
    }

}
