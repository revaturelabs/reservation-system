package com.example.reservation.payloads;

import com.example.reservation.model.IdProof;
import lombok.Data;

@Data
public class TravellerPayload {
    private String name;
    private int age;
    private boolean disabled;
    private IdProof idProof;
}
