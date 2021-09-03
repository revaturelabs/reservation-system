package com.example.reservation.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservation.model.User;
import com.example.reservation.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
    public ResponseEntity<?> post(@RequestBody User user) {
        User newUser = userService.addNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
	
}
