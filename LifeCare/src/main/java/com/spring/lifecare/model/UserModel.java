package com.spring.lifecare.model;

import java.util.List;

import com.spring.lifecare.entites.Cart;
import com.spring.lifecare.entites.MedicineDetail;
import com.spring.lifecare.entites.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {
	private int userId;
	private String userName;
	private String email;
	private String password;
	private String confirmPassword;
	private String phoneNumber;
	private String role;
	private List<MedicineDetail> medicineDetails;
	private Cart cart;

	public User getUsersDetail() {
		return new User(userId, userName, email, password, phoneNumber, role, medicineDetails, cart);
	}
}