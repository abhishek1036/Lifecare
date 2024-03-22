package com.spring.lifecare.entites;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cartItems_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemId;
	private int quantity;

	@ManyToOne(targetEntity = MedicineDetail.class)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "medicineId")
	private MedicineDetail medicineDetail;

	@ManyToOne(targetEntity = Cart.class, fetch = FetchType.EAGER)
	@JoinColumn(name = "cartId")
	private Cart cart;
}