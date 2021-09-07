package com.example.reservation.rest;

import com.example.reservation.model.Ticket;
import com.example.reservation.payloads.ResponseErrorPayload;
import com.example.reservation.payloads.TicketRefundPayload;
import com.example.reservation.payloads.TicketRequestPayload;
import com.example.reservation.payloads.TicketResponsePayload;
import com.example.reservation.service.TicketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
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

    @PutMapping("/{ticketId}")
    public ResponseEntity<?> cancelTicket(@PathVariable("ticketId") String ticketId){
       TicketRefundPayload ticketRefundPayload= ticketService.cancelTicket(new ObjectId(ticketId));
       return ResponseEntity.status(HttpStatus.OK).body(ticketRefundPayload);
    }


    @GetMapping
    public ResponseEntity<?> getUserTickets(){
        SecurityContext context = SecurityContextHolder.getContext();
        String userEmail = context.getAuthentication().getName();
        List<Ticket> tickets= ticketService.getUserTickets(userEmail);
        return  ResponseEntity.status(HttpStatus.CREATED).body(tickets);
    }


    @GetMapping("/report")
    public ResponseEntity<?> getAllTickets(){
        SecurityContext context = SecurityContextHolder.getContext();
        Collection<?> auth=context.getAuthentication().getAuthorities();

        List<String> strings=new ArrayList<>();
        for(GrantedAuthority grantedAuthority:context.getAuthentication().getAuthorities()){
            strings.add(grantedAuthority.getAuthority());
        }
         if(strings.contains("ADMIN")) {
             List<Ticket> tickets = ticketService.getAllTickets();
             return ResponseEntity.status(HttpStatus.CREATED).body(tickets);
         }else{
            throw new AccessDeniedException("Permission denied");
         }
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> handleException(Throwable e){
        ResponseErrorPayload responseErrorPayload=new ResponseErrorPayload();
        responseErrorPayload.setError(true);
        responseErrorPayload.setMessage(e.getMessage());
        return ResponseEntity.status(403).body(responseErrorPayload);
    }


}
