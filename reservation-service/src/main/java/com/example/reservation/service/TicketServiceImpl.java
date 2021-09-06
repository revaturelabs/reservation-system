package com.example.reservation.service;

import com.example.reservation.email.MailService;
import com.example.reservation.exception.ResourceNotFoundException;
import com.example.reservation.model.*;
import com.example.reservation.payloads.TicketCancelPayload;
import com.example.reservation.repository.*;
import com.example.reservation.payloads.TicketPayload;
import com.example.reservation.payloads.TravellerPayload;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PriceRepository priceRepository;

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


    @Override
    public TicketPayload bookNewTicket(TicketPayload ticketPayload) {

        double totalPrice = 0.0;

        System.out.println(ticketPayload.getTripId());

        Route route = routeRepository.findById(ticketPayload.getRouteId()).get();
        Trip trip = route.getTripList().stream().filter(t -> t.getId().equals(ticketPayload.getTripId())).findFirst().get();

        Bus bus = trip.getBus();
        BusType busType = bus.getType();
        SeatType seatType = bus.getSeatType();

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

        List<TravellerPayload> travellerList = ticketPayload.getTravellers();

        for (TravellerPayload traveller : travellerList) {
            double travellerTickerPrice;
            if (traveller.getAge() <= 4) {
                travellerTickerPrice = 0;
            } else if (traveller.getAge() >= 60 && traveller.getAge() <= 80) {
                travellerTickerPrice = tripPrice - (tripPrice * 0.15);
            } else if (traveller.getAge() > 80) {
                travellerTickerPrice = tripPrice - (tripPrice * 0.25);
            } else {
                travellerTickerPrice = tripPrice;
            }
            if (traveller.isDisabled() && traveller.getAge() >= 4) {
                travellerTickerPrice = travellerTickerPrice - (tripPrice * 0.30);
            }
            totalPrice += travellerTickerPrice;
        }


        Ticket ticket = new Ticket();

        SecurityContext context = SecurityContextHolder.getContext();
        String userEmail=context.getAuthentication().getName();
        User user=userRepository.findById(userEmail).get();
        ticket.setUser(user);

        ticket.setSource(route.getSource());
        ticket.setDestination(route.getDestination());
        ticket.setBus(bus);

        List<Traveller> travellers = new ArrayList<>();
        int i = 0;
        for (TravellerPayload travellerPayload : ticketPayload.getTravellers()) {
            Traveller traveller = modelMapper.map(travellerPayload, Traveller.class);
            traveller.setSeatNumber(ticketPayload.getSeatNumbers()[i]);
            travellers.add(traveller);
            i++;
        }
        ticket.setTravellers(travellers);

        ticket.setTravelDateTime(ticketPayload.getTravelDate());
        ticket.setSeatNumbers(ticketPayload.getSeatNumbers());
        ticket.setBookedDateTime(LocalDateTime.now());
        ticket.setStatus(TicketStatus.CONIFRMED);
        ticket.setAmount(totalPrice);


        ReservedSeats reservedSeats = reservedSeatsRepository.findReservedSeats(ticketPayload.getTravelDate().plusDays(1), bus.getNumber());
        if (reservedSeats == null) {
            System.out.println("null");
            reservedSeats = new ReservedSeats();
            reservedSeats.setTravelDate(ticketPayload.getTravelDate().plusDays(1));
            reservedSeats.setBus(bus);
        }
        int[] rSeats = new int[reservedSeats.getReservedSeats().length + ticketPayload.getSeatNumbers().length];
        System.arraycopy(reservedSeats.getReservedSeats(), 0, rSeats, 0, reservedSeats.getReservedSeats().length);
        System.arraycopy(ticketPayload.getSeatNumbers(), 0, rSeats, reservedSeats.getReservedSeats().length, ticketPayload.getSeatNumbers().length);

        reservedSeats.setReservedSeats(rSeats);

        reservedSeatsRepository.save(reservedSeats);

        Ticket savedTicket=ticketRepository.save(ticket);

        // send an email
        //mailService.sendEmail(userEmail,"Ticket booking confirmed",ticketPayload.getTripId());

        return modelMapper.map(savedTicket,TicketPayload.class);

    }


    @Override
    public List<Ticket> getTickets() {
        SecurityContext context = SecurityContextHolder.getContext();
        String userEmail=context.getAuthentication().getName();
        return ticketRepository.findAll(userEmail);
    }

    @Override
    public TicketCancelPayload cancelTicket(ObjectId ticketId) {
        Ticket ticket=  ticketRepository.findById(ticketId).orElseThrow(()-> new ResourceNotFoundException("No Such Ticket available"));
        int []seat=ticket.getSeatNumbers();
        ticket.setStatus(TicketStatus.CANCELED);
        ReservedSeats reservedSeats=reservedSeatsRepository.findReservedSeats(ticket.getTravelDateTime(),ticket.getBus().getNumber());
        int []resSeats= reservedSeats.getReservedSeats();
        Arrays.sort(resSeats);
        int resSeatIndex=  Arrays.binarySearch(resSeats,seat[0]);
        int []newReservedSeatArray= IntStream.range(0, resSeats.length)
                .filter(i -> i != resSeatIndex)
                .map(i -> resSeats[i])
                .toArray();
        reservedSeats.setReservedSeats(newReservedSeatArray);
        ticketRepository.save(ticket);
        reservedSeatsRepository.save(reservedSeats);
        TicketCancelPayload ticketCancelPayload=new TicketCancelPayload();
        ticketCancelPayload.setRefundAmount(0.0);
        return  ticketCancelPayload;
    }

    @Override
    public TicketCancelPayload cancelTicketForAPassenger(ObjectId ticketId, int seatNumber) {
        Ticket ticket=  ticketRepository.findById(ticketId).orElseThrow(()-> new ResourceNotFoundException("No Such Ticket available"));
        int []seats=ticket.getSeatNumbers();
        Arrays.sort(seats);
        int index=  Arrays.binarySearch(seats,seatNumber);
        int []newSeatList= IntStream.range(0, seats.length)
                .filter(i -> i != index)
                .map(i -> seats[i])
                .toArray();

        ticket.setSeatNumbers(newSeatList);
        for(Traveller traveller:ticket.getTravellers()){
            if(traveller.getSeatNumber()==seatNumber){
                ticket.getTravellers().remove(traveller);
                break;
            }
        }
        ticket.setStatus(TicketStatus.PARTIALlyCANCELED);

        ReservedSeats reservedSeats=reservedSeatsRepository.findReservedSeats(ticket.getTravelDateTime(),ticket.getBus().getNumber());
        int []resSeats= reservedSeats.getReservedSeats();
        Arrays.sort(resSeats);
        int resSeatIndex=  Arrays.binarySearch(resSeats,seatNumber);
        int []newReservedSeatArray= IntStream.range(0, resSeats.length)
                .filter(i -> i != resSeatIndex)
                .map(i -> resSeats[i])
                .toArray();
        reservedSeats.setReservedSeats(newReservedSeatArray);

        double refundAmt=0.0;

        //insitie the refund
        if(ChronoUnit.DAYS.between(ticket.getTravelDateTime(), LocalDate.now())>=7)
            refundAmt=ticket.getAmount()*0.85;
        else if(ChronoUnit.DAYS.between(ticket.getTravelDateTime(),LocalDate.now())>=4){
            refundAmt=ticket.getAmount()*0.50;
        }else if(ChronoUnit.DAYS.between(ticket.getTravelDateTime(),LocalDate.now())>=1){
            refundAmt=ticket.getAmount()*0.25;

        }else if(ticket.getTravelDateTime()==LocalDate.now()){
            Bus bus=ticket.getBus();
          Trip ticketTrip= (Trip) bus.getRoute().getTripList().stream().filter(trip -> trip.getBus().equals(bus));
            if((ticketTrip.getDepTime().getHour()- LocalTime.now().getHour())>=4){
                refundAmt=ticket.getAmount()*0.10;
            }else if((ticketTrip.getDepTime().getHour()- LocalDateTime.now().getHour())>=1){
                refundAmt=ticket.getAmount()*0.05;
            }else{
                refundAmt=0.0;
            }
        }
        reservedSeatsRepository.save(reservedSeats);
        ticketRepository.save(ticket);
        TicketCancelPayload ticketCancelPayload=new TicketCancelPayload();
        ticketCancelPayload.setRefundAmount(refundAmt);
        return  ticketCancelPayload;
    }
}
