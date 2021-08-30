package com.example.reservationservice.model;

import java.util.Date;
import java.util.List;

public class Ticket {

    int id;
    Date booked_date;
    Date travel_date;
    double amount;
    String source;
    String destination;
    Bus bus_ref;
    int seat_numbers;
    TicketStatus ticket_status;
    User user_ref;
    Payment txn_id;
    List<Traveller> travellers;
}
