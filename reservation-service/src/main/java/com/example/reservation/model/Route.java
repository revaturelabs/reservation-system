package com.example.reservation.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "routes")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Route {
    @Id
    private String id;
    private LocalDate travelDate;
    private String source;
    private String destination;
    private int distance;
    private List<Trip> tripList=new ArrayList<>();
}