package com.example.reservation.rest.payloads;

import com.example.reservation.model.Trip;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class RoutePayload {

    private String id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate travelDate;
    private String source;
    private String destination;
    private int distance;
    private List<TripPayload> tripList=new ArrayList<>();

}
