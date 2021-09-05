package com.example.reservation.rest.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TicketPayload {

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate travelDate;
    String routeId;
    String tripId;
    int[] seatNumbers;
    List<TravellerPayload> travellers;



}
