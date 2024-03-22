package com.spring.lifecare.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.lifecare.entites.Cart;
import com.spring.lifecare.entites.CartItems;
import com.spring.lifecare.entites.User;
import com.spring.lifecare.service.CartService;
import com.spring.lifecare.service.MedicineService;
import com.spring.lifecare.service.UserService;


@Controller
@RequestMapping("/buyer/cart")
public class CartController {
	private static final String REDIRECTTOCART = "redirect:/buyer/cart/viewCart/{userId}";
	private static final String REDIRECTTOBUYER = "redirect:/buyer";

	@Autowired
	private CartService cartService;

	@Autowired
	private MedicineService medicineService;

	@Autowired
	private UserService userService;

	@PostMapping("/add-to-cart/{userId}/{medicineId}")
	public String addToCart(@PathVariable("userId") int userId, @PathVariable("medicineId") int medicineId,
			@RequestParam("quantity") int quantity, RedirectAttributes redirectAttributes) {
		Cart cart = cartService.addMedicineToCart(userId, medicineId, quantity);

		if (Objects.nonNull(cart)) {
			redirectAttributes.addAttribute("userId", userId);
			redirectAttributes.addAttribute("medicineId", medicineId);
			redirectAttributes.addFlashAttribute("AddedToCart", true);

			return REDIRECTTOBUYER;
		}

		return REDIRECTTOBUYER;
	}

	@PostMapping("/add-to-cart-multy")
	public String addToCart(@RequestParam("userId") String userId, @RequestParam List<String> selectedMedicinesIds,
			RedirectAttributes redirectattribute) {
		int userIdInt = Integer.parseInt(userId);
		List<Integer> mrdIds = selectedMedicinesIds.stream().map(Integer::parseInt).toList();
		cartService.addToCart(userIdInt, mrdIds);
		redirectattribute.addAttribute("userIdInt", userIdInt);

		return "redirect:/buyer/cart/viewCart/{userIdInt}";
	}

	@GetMapping("/viewCart/{userId}")
	public String viewCart(@PathVariable("userId") int userId, Model model,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByEmail(userDetails.getUsername());
		int usersId = user.getUserId();
		List<CartItems> cartItems = cartService.getCartItemsByUserId(userId);
		model.addAttribute("medicine", medicineService.getAllMedicineDetails());
		model.addAttribute("userId", usersId);
		model.addAttribute("cartItems", cartItems);
		double totalPrice = cartService.calculateTotalPrice(userId);
		DecimalFormat totalPriceInDecimalFormat = new DecimalFormat("#.##");
		String formattedPrice = totalPriceInDecimalFormat.format(totalPrice);
		model.addAttribute("totalPrice", formattedPrice);

		return "cart";
	}

	@PostMapping("/delete/{userId}/{medicineId}")
	public String deleteMedicineFromCart(@PathVariable("userId") int userId,
			@PathVariable("medicineId") int medicineId) {
		cartService.deleteMedicineFromCart(userId, medicineId);

		return REDIRECTTOCART;
	}

	@GetMapping("/increment/{itemId}/{userId}")
	public String incrementCartItemQuantity(@PathVariable("itemId") int itemId, @PathVariable("userId") int userId) {
		cartService.incrementCartItemQuantity(itemId, userId);

		return REDIRECTTOCART;
	}

	@GetMapping("/decrement/{itemId}/{userId}")
	public String decrementCartItemQuantity(@PathVariable("itemId") int itemId, @PathVariable("userId") int userId) {
		cartService.decrementCartItemQuantity(itemId, userId);

		return REDIRECTTOCART;
	}
	
	@GetMapping("/paymentSuccess/{userId}")
	public String paymentSuccess(@PathVariable("userId") int userId) {
		return "paymentSuccess";
	}
}