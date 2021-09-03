package com.example.reservation.rest.payloads;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TicketPayload {

    LocalDate travelDate;
    String routeId;
    String tripId;
    int[] seatNumbers;
    List<TravellerPayload> travellers;


}
