package com.example.reservation.repository;

import com.example.reservation.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {

    List<User> findByMobile(String mobile);

    User findByEmail(String email);
}
