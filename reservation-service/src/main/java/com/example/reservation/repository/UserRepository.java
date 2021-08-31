package com.example.reservation.repository;

import com.example.reservation.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByMobile(String mobile);

}
