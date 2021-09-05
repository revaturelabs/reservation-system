package com.example.reservation.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "buses")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Bus {

    @Id
    private String number;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BusType type;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private SeatType seatType;
    private int seats;
    private BusContact contact;
    @JsonIgnore
    @DBRef
    private Route route;


}
