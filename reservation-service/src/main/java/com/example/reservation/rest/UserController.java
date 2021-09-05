package com.example.reservation.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.reservation.exception.ResourceConflictExeception;
import com.example.reservation.model.User;
import com.example.reservation.rest.payloads.ResponseErrorPayload;
import com.example.reservation.rest.payloads.UserPayload;
import com.example.reservation.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody User user) {
        User newUser = userService.addNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }


    @GetMapping(params = {"!by","!value"})
    public ResponseEntity<?> getAll() {
        List<UserPayload> users = userService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    // /api/users?by=email&value=nag@email.com

    @GetMapping(params = {"by", "value"})
    public ResponseEntity<?> getByEmail(@RequestParam("by") String by, @RequestParam("value") String value) {
        UserPayload user = null;
        if (by.equals("email")) {
            user = userService.getUserByEmail(value);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        if (by.equals("mobile")) {
            List<UserPayload> users = userService.getUserByMobile(value);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(value = {ResourceConflictExeception.class})
    public ResponseErrorPayload exceptionHandler(Throwable t) {
        ResponseErrorPayload responseError = new ResponseErrorPayload();
        responseError.setError(true);
        responseError.setMessage(t.getMessage());
        return responseError;
    }

}
