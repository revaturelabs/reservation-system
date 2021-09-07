package com.example.reservation.rest;

import com.example.reservation.exception.ResourceConflictExeception;
import com.example.reservation.model.Bus;
import com.example.reservation.payloads.ResponseErrorPayload;
import com.example.reservation.service.BusService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
@CrossOrigin("/*")
=======

@Tag(name = "bus", description ="REST API for bus resource")
>>>>>>> a6462d0ae19233da43b127437596f74011a53c5d
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
