package com.example.reservationservice.model;


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

import java.time.LocalTime;
import java.util.List;

@Document
@NoArgsConstructor
@Data
@AllArgsConstructor
public class Route {

    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using= ToStringSerializer.class)
    private ObjectId route_id;
    private String source;
    private String destination;
    private int distance;
    private LocalTime dep_time;
    private LocalTime arrival_time;
    private List<BusStopPoint> stop_points;
    @DBRef
    private List<Bus> bus_list;


}