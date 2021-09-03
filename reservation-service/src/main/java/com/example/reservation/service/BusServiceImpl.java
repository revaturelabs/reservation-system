package com.example.reservation.service;

import com.example.reservation.exception.ResourceConflictExeception;
import com.example.reservation.model.Bus;
import com.example.reservation.repository.BusRepository;
import com.mongodb.MongoWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    BusRepository busRepository;

    @Override
    public Bus addNewBus(Bus bus) {
        Bus savedBus=null;
        try {
            savedBus = busRepository.insert(bus);
        } catch (DuplicateKeyException e) {
            throw new ResourceConflictExeception("bus already exist");
        }
        return  savedBus;
    }


//    @Override
//    public void addBusToRoute(String routeId, Bus bus) {
//
//    }
//
//    @Override
//    public void removeBusFromRoute(String routeId) {
//
//    }
//
//    @Override
//    public List<Bus> getAll() {
//        return null;
//    }
//
//    @Override
//    public List<Bus> getByRoute(String routeId) {
//        return null;
//    }
//
//    @Override
//    public Bus getByTrip(String tripCode) {
//        return null;
//    }
}
