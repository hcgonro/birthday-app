package com.app.birthday.controllers;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.birthday.models.User;
import com.app.birthday.repositoris.UserRepository;

@RestController
public class UserController {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@GetMapping("/birthdays/users/{userName}")
	private User getUser(@PathVariable String userName) {
		return userRepository.findByUserName(userName);
	}
	
	@GetMapping("/birthdays/users/")
	private List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/birthdays/users/")
	private User postUser(@RequestBody User newUser) {
		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
		return userRepository.insert(newUser);
	}
	
}