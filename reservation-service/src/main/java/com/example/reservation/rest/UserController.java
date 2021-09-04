package com.example.reservation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.reservation.exception.ResourceConflictExeception;
import com.example.reservation.model.User;
import com.example.reservation.rest.payloads.ResponseErrorPayload;
import com.example.reservation.rest.payloads.UserPayload;
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
	
	@ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {ResourceConflictExeception.class})
    public ResponseErrorPayload exceptionHandler(Throwable t) {
        ResponseErrorPayload responseError = new ResponseErrorPayload();
        responseError.setError(true);
        responseError.setMessage(t.getMessage());
        return responseError;
    }
	
	@GetMapping("/all_users")
	public ResponseEntity<?> getAll(){
		List<UserPayload> users = userService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(users);
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<?> getByEmail(@PathVariable("email") String email){
		UserPayload user = userService.getUserByEmail(email);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/mobile/{mobile}")
	public ResponseEntity<?> getByMobile(@PathVariable("mobile") String mobile){
		UserPayload user = userService.getUserByMobile(mobile);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
}
