package com.spring.lifecare.service;

import com.spring.lifecare.entites.User;

/**
 * The UserService interface provides methods for managing user-related
 * operations.
 */
public interface UserService {

	/**
	 * Creates a new user detail.
	 *
	 * @param users The User object containing user details.
	 * @return The created User object.
	 */
	User createUserDetail(User users);

	/**
	 * Finds a user by their email address.
	 *
	 * @param email The email address to search for.
	 * @return The User object associated with the email, or null if not found.
	 */
	User findUserByEmail(String email);


	/**
	 * Gets a user by their user ID.
	 *
	 * @param userId The ID of the user to retrieve.
	 * @return The User object associated with the user ID, or null if not found.
	 */
	User getUserByuserId(int userId);
}