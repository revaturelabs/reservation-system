package com.example.reservation.rest;

import com.example.reservation.exception.ResourceConflictExeception;
import com.example.reservation.model.Bus;
import com.example.reservation.rest.payloads.ResponseErrorPayload;
import com.example.reservation.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Bus bus) {
        Bus savedBus = busService.addNewBus(bus);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBus);
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
