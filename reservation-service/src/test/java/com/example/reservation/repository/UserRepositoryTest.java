package com.example.reservation.repository;

import com.example.reservation.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

@DataMongoTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void beforeEach(){
        User user=new User();
        user.setEmail("user1@email.com");
        user.setMobile("1234567890");
        user.setName("user1");
        user.setDob(LocalDate.now());
        user.setGender(Gender.MALE);
        user.setAvatar("user1.jpg");
        //user.setAddress(new Address());
        IdProof  idProof=new IdProof();
        idProof.setType("adhar");
        idProof.setNumber("1243-2122-3434");
        user.setIdProof(idProof);
        userRepository.save(user);
    }

    @Test
    public void saveNewUser(){
        User savedUser= userRepository.findById("user1@email.com").orElse(null);
        assertNotNull(savedUser);
    }


    @Test
    public void addNewTravllers(){

        Traveller traveller1=new Traveller();
        traveller1.setName("T1");
        traveller1.setAge(22);
        traveller1.setIdProof(new IdProof());
        traveller1.setDisabled(false);


        Traveller traveller2=new Traveller();
        traveller2.setName("T2");
        traveller2.setAge(25);
        traveller2.setIdProof(new IdProof());
        traveller2.setDisabled(true);

        User user=userRepository.findById("user1@email.com").get();
        user.setTravellers(List.of(traveller1,traveller2));

        userRepository.save(user);

        User updatedUser=userRepository.findById("user1@email.com").get();
        assertEquals(2,updatedUser.getTravellers().size());


    }


    public void afterEach(){
        userRepository.deleteAll();
    }




}
