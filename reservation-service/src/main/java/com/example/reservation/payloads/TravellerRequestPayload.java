package com.example.reservation.payloads;

import com.example.reservation.model.IdProof;
import lombok.Data;

@Data
public class TravellerRequestPayload {
    private String name;
    private int age;
    private boolean disabled;
    private IdProof idProof;
}
