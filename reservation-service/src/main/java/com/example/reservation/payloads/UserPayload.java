package com.example.reservation.payloads;

import java.time.LocalDate;
import java.util.List;

import com.example.reservation.model.Address;
import com.example.reservation.model.Gender;
import com.example.reservation.model.IdProof;
import com.example.reservation.model.Traveller;

import lombok.Data;

@Data
public class UserPayload {

	private String email;
    private String password;
    private String name;
    private String mobile;
    private LocalDate dob;
    private Gender gender;
    private String avatar;
    private Address address;
    private IdProof idProof;
    private List<Traveller> travellers;
	
}
