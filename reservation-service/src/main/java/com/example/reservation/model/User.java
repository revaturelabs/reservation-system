package com.example.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@NoArgsConstructor
@Data
@AllArgsConstructor
public class User {
    private String name;
    @Id
    private String email;
    private String mobile;
    private Date dob;
    private Gender gender;
    private String avatar;
    private Address address;
    private IdProof idProof;
    private Authoritiy authority;
    private List<Traveller> travellers;


}