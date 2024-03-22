package com.spring.lifecare.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.spring.lifecare.entites.User;
import com.spring.lifecare.exception.ServiceException;
import com.spring.lifecare.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;


	@Override
	public User createUserDetail(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			log.info("Saving User Details: {}", user);

			return userRepository.save(user);
		} catch (Exception e) {
			log.error("Exception occurred while saving user details: {}", e.getMessage(), e);

			throw new ServiceException("Failed to create User Details");
		}
	}

	@Override
	public User findUserByEmail(String email) {
		try {
			log.info("Found User By Email:{}", email);

			return userRepository.findByEmail(email);
		} catch (Exception e) {
			log.error("Exception occurred while finding user by email: {}", e.getMessage(), e);

			throw new ServiceException("Failed to find User by email");
		}
	}


	@Override
	public User getUserByuserId(int userId) throws ServiceException {
		return userRepository.getUserByuserId(userId);
	}
}