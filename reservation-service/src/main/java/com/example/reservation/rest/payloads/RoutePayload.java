package com.example.reservation.rest.payloads;

import com.example.reservation.model.Trip;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoutePayload {

    private String id;
    private String source;
    private String destination;
    private int distance;
    private List<TripPayload> tripList=new ArrayList<>();

}
