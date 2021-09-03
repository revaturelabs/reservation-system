package com.example.reservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.example.reservation.exception.ResourceConflictExeception;
import com.example.reservation.model.User;
import com.example.reservation.repository.UserRepository;
import com.example.reservation.rest.payloads.UserPayload;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addNewUser(User user) {
		User newUser = null;
		try {
			newUser = userRepository.insert(user);
		} catch (DuplicateKeyException e) {
			throw new ResourceConflictExeception("user already exist");
		}
		return newUser;
	}

	@Override
	public List<UserPayload> getAll() {
		List<User> users = userRepository.findAll();
		List<UserPayload> usersPaylaods = new ArrayList<>();
		for(User user:users) {
			UserPayload userPayload = new UserPayload();
			userPayload.setName(user.getName());
			userPayload.setEmail(user.getEmail());
			userPayload.setMobile(user.getMobile());
			userPayload.setDob(user.getDob());
			userPayload.setGender(user.getGender());
			userPayload.setAvatar(user.getAvatar());
			userPayload.setAddress(user.getAddress());
			userPayload.setIdProof(user.getIdProof());
			userPayload.setTravellers(user.getTravellers());
			usersPaylaods.add(userPayload);
		}
		return usersPaylaods;
	}

	@Override
	public UserPayload getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);

		UserPayload userPayload = new UserPayload();
		userPayload.setName(user.getName());
		userPayload.setEmail(user.getEmail());
		userPayload.setMobile(user.getMobile());
		userPayload.setDob(user.getDob());
		userPayload.setGender(user.getGender());
		userPayload.setAvatar(user.getAvatar());
		userPayload.setAddress(user.getAddress());
		userPayload.setIdProof(user.getIdProof());
		userPayload.setTravellers(user.getTravellers());

		return userPayload;

	}

	@Override
	public UserPayload getUserByMobile(String mobile) {
		User user = userRepository.findByMobile(mobile);

		UserPayload userPayload = new UserPayload();
		userPayload.setName(user.getName());
		userPayload.setEmail(user.getEmail());
		userPayload.setMobile(user.getMobile());
		userPayload.setDob(user.getDob());
		userPayload.setGender(user.getGender());
		userPayload.setAvatar(user.getAvatar());
		userPayload.setAddress(user.getAddress());
		userPayload.setIdProof(user.getIdProof());
		userPayload.setTravellers(user.getTravellers());

		return userPayload;

	}
}
