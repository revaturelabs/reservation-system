package com.project.bus_reservation.model;

import java.util.Date;
import java.util.List;

public class User {
    String name;
    String email;
    String mobile;
    Date dob;
    Gender gender;
    String avatar;
    Address address;
    IdProof idProof;
    Authoritiy authority;
    List<Traveller> travellers;

}