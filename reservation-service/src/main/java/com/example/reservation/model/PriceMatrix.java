package com.example.reservation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "price-matrix")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PriceMatrix {

    @Id
    private ObjectId id;
    private BusType busType;
    private SeatType seatType;
    private double basePrice;


}
