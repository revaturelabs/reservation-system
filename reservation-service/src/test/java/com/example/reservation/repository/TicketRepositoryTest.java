package com.example.reservation.repository;


import com.example.reservation.model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import  static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class TicketRepositoryTest {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BusRepository busRepository;

    @BeforeEach
    public void beforeEach(){
        Ticket ticket=new Ticket();
        ticket.setBookedDateTime(LocalDateTime.now());
        ticket.setTravelDate(LocalDate.of(2021,9,1));
        ticket.setAmount(950);
        ticket.setSource("BENGALURU");
        ticket.setDestination("CHENNAI");
        ticket.setSeatNumbers(List.of(12,13));
        ticket.setStatus(TicketStatus.CONIFRMED);

        List<Traveller> travellers=List.of(
                new Traveller("Nag",new IdProof("adahar","1212-1212-1212-1212"),38,false,0),
                new Traveller("Indu",new IdProof("adahar","4344-3434-3434-3434"),33,false,0)
        );

        ticket.setTravellers(travellers);

        Bus bus=new Bus();
        bus.setNumber("TN18BZ1109");
        ticket.setBus(bus);

        User user=new User();
        user.setEmail("user1@email.com");

        ticket.setUser(user);

        userRepository.save(user);
        busRepository.save(bus);
        ticketRepository.save(ticket);
    }

    @Test
    public void saveTicket(){
        List<Ticket> tickets=ticketRepository.findAll("user1@email.com");
        assertEquals(1,tickets.size());
    }

    @Test
    public void getTicketStatus(){
        List<Ticket> tickets=ticketRepository.findAll("user1@email.com");
        Ticket ticket=tickets.get(0);
        assertEquals(TicketStatus.CONIFRMED,ticket.getStatus());
    }

    @Test
    public void cancelTicket(){
        List<Ticket> tickets=ticketRepository.findAll("user1@email.com");
        Ticket ticket=tickets.get(0);
        ticket.setStatus(TicketStatus.CANCELED);
        ticketRepository.save(ticket);
        List<Ticket> updatedTickets=ticketRepository.findAll("user1@email.com");
        ticket=tickets.get(0);
        assertEquals(TicketStatus.CANCELED,ticket.getStatus());
    }

    @Test
    public void removeOneTraveler(){
        List<Ticket> tickets=ticketRepository.findAll("user1@email.com");
        Ticket ticket=tickets.get(0);
        ticket.getTravellers().remove(1);
        ticketRepository.save(ticket);

        tickets=ticketRepository.findAll("user1@email.com");
        ticket=tickets.get(0);
        assertEquals(1,ticket.getTravellers().size());
    }



    @AfterEach
    public void afterEach(){
        ticketRepository.deleteAll();
    }

}
