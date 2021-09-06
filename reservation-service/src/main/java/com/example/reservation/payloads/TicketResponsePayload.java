package com.example.reservation.payloads;

import com.example.reservation.model.TicketStatus;
import com.example.reservation.model.Traveller;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketResponsePayload {

    private ObjectId id;
    private LocalDate travelDate;
    private double amount;
    private String source;
    private String destination;
    private List<Integer> seatNumbers;
    private TicketStatus status;
    private List<Traveller> travellers;
    private LocalDateTime bookedDateTime;
    private TripResponsePayload trip;
    private BusResponsePayload bus;

}
