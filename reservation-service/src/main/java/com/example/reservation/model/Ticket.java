package com.example.reservation.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Document(collection = "tickets")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Ticket {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    private LocalDateTime bookedDateTime;
    private LocalDate travelDate;
    private double amount;
    private String source;
    private String destination;
    private List<Integer> seatNumbers;
    private TicketStatus status;
    private List<Traveller> travellers;
    @JsonIgnore
    @DBRef(lazy = true)
    private Bus bus;
    @DBRef(lazy = true)
    @JsonIgnore
    private User user;
}
