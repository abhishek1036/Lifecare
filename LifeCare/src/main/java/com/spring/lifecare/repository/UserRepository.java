package com.spring.lifecare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.lifecare.entites.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);

	User findByUserName(String userName);

	User getUserByuserId(Integer userId);

}