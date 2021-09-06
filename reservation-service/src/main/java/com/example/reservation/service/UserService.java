package com.example.reservation.service;


import com.example.reservation.model.User;
import com.example.reservation.payloads.UserPayload;

import java.util.List;

public interface UserService {

   User addNewUser(User user);
   List<UserPayload> getAll();
   UserPayload getUserByEmail(String email);
   List<UserPayload> getUserByMobile(String mobile);


}
