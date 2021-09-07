package com.example.reservation.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TicketRequestPayload {

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate travelDate;
    String routeId;
    String tripId;
    List<Integer> seatNumbers;
    List<TravellerRequestPayload> travellers;
    String userEmail;



}
