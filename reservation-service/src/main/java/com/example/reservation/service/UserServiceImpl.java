package com.example.reservation.service;

import com.example.reservation.exception.ResourceConflictExeception;
import com.example.reservation.model.User;
import com.example.reservation.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    
	@Autowired
	private UserRepository userRepository;
	
	@Override
    public User addNewUser(User user) {
    	User newUser=null;
        try {
            newUser = userRepository.insert(user);
        } catch (DuplicateKeyException e) {
            throw new ResourceConflictExeception("user already exist");
        }
        return newUser;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserByMobile(String mobile) {
        return null;
    }
}
