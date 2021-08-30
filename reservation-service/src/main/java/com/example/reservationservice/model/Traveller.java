package com.example.reservationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class Traveller {
    private String name;
    private IdProof idProof;
    private int age;
    private boolean disabled;

}
