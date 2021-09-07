package com.example.reservation.service;

import com.example.reservation.email.MailService;
import com.example.reservation.model.*;
import com.example.reservation.payloads.*;
import com.example.reservation.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ReservedSeatsRepository reservedSeatsRepository;

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;


    // Price Matrix
    private double getTripPrice(Route route, BusType busType, SeatType seatType) {
        double tripPrice = 7 * route.getDistance();
        if (busType.equals(BusType.NON_AC) && seatType.equals(SeatType.SLEEPER)) {
            tripPrice += 100;
        }
        if (busType.equals(BusType.AC) && seatType.equals(SeatType.CHAIR)) {
            tripPrice += 200;
        }
        if (busType.equals(BusType.AC) && seatType.equals(SeatType.SLEEPER)) {
            tripPrice += 300;
        }
        return tripPrice;
    }


    private double getTotalPrice(double tripPrice, List<TravellerRequestPayload> travellerList) {
        double totalPrice = 0;
        for (TravellerRequestPayload traveller : travellerList) {
            double travellerTicketPrice = 0;
            if (traveller.getAge() <= 4) {
                travellerTicketPrice = 0;
            } else if (traveller.getAge() >= 60 && traveller.getAge() <= 80) {
                travellerTicketPrice = tripPrice - (tripPrice * 0.15);
            } else if (traveller.getAge() > 80) {
                travellerTicketPrice = tripPrice - (tripPrice * 0.25);
            } else {
                travellerTicketPrice = tripPrice;
            }
            if (traveller.isDisabled() && traveller.getAge() >= 4) {
                travellerTicketPrice = travellerTicketPrice - (tripPrice * 0.30);
            }
            totalPrice += travellerTicketPrice;
        }
        return totalPrice;
    }


    @Override
    public TicketResponsePayload bookNewTicket(TicketRequestPayload ticketRequestPayload) {

        Route route = routeRepository.findById(ticketRequestPayload.getRouteId()).get();
        Trip trip = route.getTripList().stream().filter(t -> t.getId().equals(ticketRequestPayload.getTripId())).findFirst().get();
        Bus bus = trip.getBus();
        BusType busType = bus.getType();
        SeatType seatType = bus.getSeatType();

        double tripPrice = getTripPrice(route, busType, seatType);

        List<TravellerRequestPayload> travellerList = ticketRequestPayload.getTravellers();

        double totalPrice = getTotalPrice(tripPrice,travellerList);


        Ticket ticket = new Ticket();

        User user = userRepository.findById(ticketRequestPayload.getUserEmail()).get();
        ticket.setUser(user);

        ticket.setSource(route.getSource());
        ticket.setDestination(route.getDestination());
        ticket.setBus(bus);

        List<Traveller> travellers = new ArrayList<>();
        int i = 0;
        for (TravellerRequestPayload travellerRequestPayload : ticketRequestPayload.getTravellers()) {
            Traveller traveller = modelMapper.map(travellerRequestPayload, Traveller.class);
            traveller.setSeatNumber(ticketRequestPayload.getSeatNumbers().get(i));
            travellers.add(traveller);
            i++;
        }
        ticket.setTravellers(travellers);

        ticket.setTravelDate(ticketRequestPayload.getTravelDate());
        ticket.setSeatNumbers(ticketRequestPayload.getSeatNumbers());
        ticket.setBookedDateTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.CONIFRMED);
        ticket.setAmount(totalPrice);


        ReservedSeats reservedSeats = reservedSeatsRepository.findReservedSeats(ticketRequestPayload.getTravelDate().plusDays(1), bus.getNumber());
        if (reservedSeats == null) {
            System.out.println("null");
            reservedSeats = new ReservedSeats();
            reservedSeats.setTravelDate(ticketRequestPayload.getTravelDate().plusDays(1));
            reservedSeats.setBus(bus);
        }

        reservedSeats.getReservedSeats().addAll(ticketRequestPayload.getSeatNumbers());

        reservedSeatsRepository.save(reservedSeats);

        Ticket savedTicket = ticketRepository.save(ticket);

        TicketResponsePayload ticketResponsePayload=modelMapper.map(savedTicket,TicketResponsePayload.class);
        ticketResponsePayload.setBus(modelMapper.map(bus, BusResponsePayload.class));
        ticketResponsePayload.setTrip(modelMapper.map(trip, TripResponsePayload.class));

        // send an email
        //mailService.sendEmail(ticketRequestPayload.getUserEmail(), "Ticket booking confirmed", ticketRequestPayload.getTripId());

        return ticketResponsePayload;

    }


    @Override
    public List<Ticket> getTickets() {
        SecurityContext context = SecurityContextHolder.getContext();
        String userEmail = context.getAuthentication().getName();
        return ticketRepository.findAll(userEmail);
    }
}
