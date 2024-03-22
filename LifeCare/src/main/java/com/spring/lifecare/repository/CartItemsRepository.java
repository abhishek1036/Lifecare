package com.spring.lifecare.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.lifecare.entites.CartItems;


@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer> {
	List<CartItems> findByCart_User_userId(int userId);

	CartItems findById(int itemId);
}
