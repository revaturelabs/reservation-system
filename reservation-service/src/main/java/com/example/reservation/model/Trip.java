package com.example.reservation.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalTime;

@Data
public class Trip {

    private String id;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime depTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;
    @DBRef
    private Bus bus;

}
