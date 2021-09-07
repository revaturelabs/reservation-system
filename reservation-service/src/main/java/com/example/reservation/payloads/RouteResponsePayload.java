package com.example.reservation.payloads;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class RouteResponsePayload {

    private String id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate travelDate;
    private String source;
    private String destination;
    private int distance;
    private List<TripResponsePayload> tripList=new ArrayList<>();

}
