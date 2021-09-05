package com.example.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "users")
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    @Id
    private String email;
    private String password;
    private String name;
    private String mobile;
    private LocalDate dob;
    private Gender gender;
    private String avatar;
//    private Address address;
    private IdProof idProof;
    private List<Traveller> travellers;
}