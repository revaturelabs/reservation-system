package com.example.reservation.service;

import com.example.reservation.model.User;
import com.example.reservation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String email){
        Optional<User> user=userRepository.findById(email);
        return user.orElse(null);
    }

}
