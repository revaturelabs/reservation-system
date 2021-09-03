package com.example.reservation.service;

import com.example.reservation.model.Ticket;

import java.util.List;

public class TicketServiceImpl implements TicketService {

        @Autowired
    PriceRepository priceRepository;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    ReservedSeatsRepository reservedSeatsRepository;

    @Override
    public void bookNewTicket(Ticket ticket) {
        double totalPrice=0.0;

        Bus bus=ticket.getBus();
        double basePrice=0.0;

        int totalDistance=0;
        basePrice= priceRepository.findPriceMatrix(bus.getType(),bus.getSeatType()).getBasePrice();
        if(ticket.getSource().equals(bus.getRoute().getSource())){
            totalDistance=ticket.getBus().getRoute().getDistance();

        }else{

            List<StopPoint> stopPoints=null;
            //bus.getRoute().getTripList();
            for(StopPoint stopPoint:stopPoints) {
                if (stopPoint.getName().equals(ticket.getSource())) {
                    totalDistance = ticket.getBus().getRoute().getDistance() - stopPoint.getDistanceFromSource();
                    break;
                }
            }
        }

        basePrice=basePrice*totalDistance;

        List<Traveller> travellerList= ticket.getTravellers();

        for (Traveller traveller:travellerList){
            double travellerTickerPrice;
            if(traveller.getAge()<=4){
                travellerTickerPrice=0;
            }else if(traveller.getAge()>=60 && traveller.getAge()<=80){
                travellerTickerPrice=basePrice*0.15;
            }else if(traveller.getAge()>80){
                travellerTickerPrice=basePrice*0.25;
            }else{
                travellerTickerPrice=basePrice;
            }
            if(traveller.isDisabled()){
                // travellerTickerPrice=travellerTickerPrice*0.30;

                travellerTickerPrice=travellerTickerPrice-basePrice*0.30;
            }
            totalPrice+=travellerTickerPrice;
        }
        ticket.setAmount(totalPrice);

        int []reservedSeats=ticket.getSeatNumbers();

        ReservedSeats reservedSeats1=new ReservedSeats();
        reservedSeats1.setBus(bus);
        reservedSeats1.setTravel_date(ticket.getTravelDateTime());
        reservedSeats1.getReservedSeats().addAll(new ArrayList(Arrays.asList(reservedSeats)));

        reservedSeatsRepository.save(reservedSeats1);

        ticket.setStatus(TicketStatus.CONIFRMED);

        ticketRepository.save(ticket);
    
    }

    @Override
    public List<Ticket> getTickets(String user) {
        return null;
    }

    @Override
    public void cancelTicket(ObjectId ticketId) {
        Ticket ticket=ticketRepository.findById(ticketId).get();

        double refundAmt=0.0;

        //remove the traveller from the travellers list

      //  ticket.getTravellers().remove(traveller);

        //free the seat




        /*

       	Cancellation number will be generated - unique number - and should be allowed to print5.



         */
        //insitie the refund
        if(ChronoUnit.DAYS.between(ticket.getTravelDateTime().toLocalDate(),LocalDate.now())>=7)
            refundAmt=ticket.getAmount()*0.85;
        else if(ChronoUnit.DAYS.between(ticket.getTravelDateTime().toLocalDate(),LocalDate.now())>=4){
            refundAmt=ticket.getAmount()*0.50;
        }else if(ChronoUnit.DAYS.between(ticket.getTravelDateTime().toLocalDate(),LocalDate.now())>=1){
            refundAmt=ticket.getAmount()*0.25;
        }else if(ticket.getTravelDateTime().toLocalDate()== LocalDate.now()){
            if((ticket.getTravelDateTime().getHour()- LocalDateTime.now().getHour())>=4){
                refundAmt=ticket.getAmount()*0.10;
            }else if((ticket.getTravelDateTime().getHour()- LocalDateTime.now().getHour())>=1){
                refundAmt=ticket.getAmount()*0.05;
            }else{
                refundAmt=0.0;
            }
        }
    }
}
