package com.example.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Document
public class ReservedSeats {

    @Id
    ObjectId id;
    private Date travel_date;
    private List<Integer> reservedSeats;
    @DBRef
    private Bus bus;



}


