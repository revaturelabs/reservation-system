package com.example.reservation.service;


import com.example.reservation.model.User;

import java.util.List;

public interface UserService {

   void addNewUser(User user);
   List<User> getAll();
   User getUserByEmail(String email);
   User getUserByMobile(String mobile);

}
