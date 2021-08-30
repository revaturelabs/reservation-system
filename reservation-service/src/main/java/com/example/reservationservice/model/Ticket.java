package com.example.reservationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@NoArgsConstructor
@Data
@AllArgsConstructor

public class Ticket {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using= ToStringSerializer.class)
    @Id
    private ObjectId id;
    private Date booked_date;
    private Date travel_date;
    private double amount;
    private String source;
    private String destination;
    @DBRef
    private Bus bus_ref;
    private int seat_numbers;
    private TicketStatus ticket_status;
    @DBRef
    private User user_ref;
    @DBRef
    private Payment txn_id;
    private List<Traveller> travellers;
}
