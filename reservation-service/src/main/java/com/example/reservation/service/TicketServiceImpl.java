package com.example.reservation.service;

import com.example.reservation.model.*;
import com.example.reservation.repository.PriceRepository;
import com.example.reservation.repository.ReservedSeatsRepository;
import com.example.reservation.repository.RouteRepository;
import com.example.reservation.repository.TicketRepository;
import com.example.reservation.rest.payloads.TicketPayload;
import com.example.reservation.rest.payloads.TravellerPayload;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ReservedSeatsRepository reservedSeatsRepository;

    @Autowired
    RouteRepository routeRepository;


    @Override
    public TicketPayload bookNewTicket(TicketPayload ticketPayload) {

        double totalPrice = 0.0;

        System.out.println(ticketPayload.getTripId());

        Route route=routeRepository.findById(ticketPayload.getRouteId()).get();
        Trip trip=route.getTripList().stream().filter(t->t.getId().equals(ticketPayload.getTripId())).findFirst().get();

        Bus bus=trip.getBus();
        BusType busType=bus.getType();
        SeatType seatType=bus.getSeatType();

        double tripPrice =7*route.getDistance();

        if(busType.equals(BusType.NON_AC) && seatType.equals(SeatType.SLEEPER)){
            tripPrice+=100;
        }
        if(busType.equals(BusType.AC) && seatType.equals(SeatType.CHAIR)){
            tripPrice+=200;
        }
        if(busType.equals(BusType.AC) && seatType.equals(SeatType.SLEEPER)){
            tripPrice+=300;
        }


        List<TravellerPayload> travellerList = ticketPayload.getTravellers();

        for (TravellerPayload traveller : travellerList) {
            double travellerTickerPrice;
            if (traveller.getAge() <= 4) {
                travellerTickerPrice = 0;
            } else if (traveller.getAge() >= 60 && traveller.getAge() <= 80) {
                travellerTickerPrice = tripPrice * 0.15;
            } else if (traveller.getAge() > 80) {
                travellerTickerPrice = tripPrice * 0.25;
            } else {
                travellerTickerPrice = tripPrice;
            }
            if (traveller.isDisabled()) {
                // travellerTickerPrice=travellerTickerPrice*0.30;
                travellerTickerPrice = travellerTickerPrice - tripPrice * 0.30;
            }
            totalPrice += travellerTickerPrice;
        }

        Ticket ticket=new Ticket();
        ticket.setSource(route.getSource());
        ticket.setDestination(route.getDestination());
        ticket.setBus(bus);
        ticket.setTravellers(null);
        ticket.setTravelDateTime(ticketPayload.getTravelDate());
        ticket.setUser(null);
        ticket.setSeatNumbers(ticketPayload.getSeatNumbers());
        ticket.setBookedDateTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.CONIFRMED);
        ticket.setAmount(totalPrice);

        ReservedSeats dbReservedSeats=new ReservedSeats();
        dbReservedSeats.setDate(LocalDate.now());
        dbReservedSeats.setBus(bus);
        dbReservedSeats.setReservedSeats(ticketPayload.getSeatNumbers());

        reservedSeatsRepository.save(dbReservedSeats); // re-work

        ticketRepository.save(ticket);

        return ticketPayload;
    }

}
