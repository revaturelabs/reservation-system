package com.example.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Payment {

    @Id
    private ObjectId id;
    private Date date;
    private double amount;

}
