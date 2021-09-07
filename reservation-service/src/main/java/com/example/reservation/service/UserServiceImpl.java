package com.example.reservation.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.reservation.exception.ResourceConflictExeception;
import com.example.reservation.model.User;
import com.example.reservation.repository.UserRepository;
import com.example.reservation.payloads.UserRequestPayload;

@Service
public class UserServiceImpl implements UserService {


	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User addNewUser(User user) {
		User newUser = null;
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			newUser = userRepository.insert(user);
		} catch (DuplicateKeyException e) {
			throw new ResourceConflictExeception("user already exist");
		}
		return newUser;
	}

	@Override
	public List<UserRequestPayload> getAll() {
		List<User> users = userRepository.findAll();
		List<UserRequestPayload> usersPaylaods = new ArrayList<>();
		for(User user:users) {
			UserRequestPayload userPayload=modelMapper.map(user, UserRequestPayload.class);
			usersPaylaods.add(userPayload);
		}
		return usersPaylaods;
	}

	@Override
	public UserRequestPayload getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		UserRequestPayload userPayload=modelMapper.map(user, UserRequestPayload.class);
		return userPayload;

	}

	@Override
	public List<UserRequestPayload> getUserByMobile(String mobile) {
		List<User> users = userRepository.findByMobile(mobile);
		List<UserRequestPayload> usersPaylaods = new ArrayList<>();
		for(User user:users) {
			UserRequestPayload userPayload=modelMapper.map(user, UserRequestPayload.class);
			usersPaylaods.add(userPayload);
		}
		return usersPaylaods;
	}
}
