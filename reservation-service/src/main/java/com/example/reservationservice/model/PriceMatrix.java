package com.example.reservationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@NoArgsConstructor
@Data
@AllArgsConstructor
public class PriceMatrix {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId id;
    private BusType bus_type;
    private SeatType seat_type;
    private double per_km_price;
}
