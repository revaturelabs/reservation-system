package com.example.reservation.rest.payloads;

import com.example.reservation.model.IdProof;
import lombok.Data;

@Data
public class TravellerPayload {
    private String name;
    private IdProof idProof;
    private int age;
    private boolean disabled;
}
