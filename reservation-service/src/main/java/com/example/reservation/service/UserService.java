package com.example.reservation.service;


import com.example.reservation.model.User;
import com.example.reservation.payloads.UserRequestPayload;

import java.util.List;

public interface UserService {

   User addNewUser(User user);
   List<UserRequestPayload> getAll();
   UserRequestPayload getUserByEmail(String email);
   List<UserRequestPayload> getUserByMobile(String mobile);


}
