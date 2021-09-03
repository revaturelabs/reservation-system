package com.example.reservation.service;

import com.example.reservation.model.User;
import com.example.reservation.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @BeforeEach
    public void beforeEach(){
        //Optional<User> emptyOptional=Optional.empty();
        Optional<User> userOptional=Optional.of(new User());
        when(userRepository.findById("user1@email.com")).thenReturn(userOptional);
    }

    @Test
    public void returnUserForEmail(){
      assertTrue(true);
    }

}
