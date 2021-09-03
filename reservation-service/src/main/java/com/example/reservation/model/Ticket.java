package com.example.reservation.model;


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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Document
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Ticket {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    private LocalDateTime bookedDateTime;
    private LocalDate travelDateTime;
    private double amount;
    private String source;
    private String destination;
    private int[] seatNumbers;
    private TicketStatus status;
    private List<Traveller> travellers;
    @DBRef(lazy = true)
    private Bus bus;
    @DBRef(lazy = true)
    private User user;
//    @DBRef
//    private Payment payment;
}
