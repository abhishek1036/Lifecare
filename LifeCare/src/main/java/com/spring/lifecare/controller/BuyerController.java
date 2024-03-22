package com.spring.lifecare.controller;

import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.lifecare.entites.MedicineDetail;
import com.spring.lifecare.entites.User;
import com.spring.lifecare.service.MedicineService;
import com.spring.lifecare.service.UserService;

import io.micrometer.common.util.StringUtils;

@Controller
public class BuyerController {
	public static final String BUYER = "buyer";

	@Autowired
	private MedicineService medicineService;

	@Autowired
	private UserService userService;



	@GetMapping("/" + BUYER)
	public String buyerPage(Model model, @RequestParam(required = false) String searchTerm,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByEmail(userDetails.getUsername());
		int userId = user.getUserId();
		List<MedicineDetail> medicines;

		if (Objects.nonNull(searchTerm) && !searchTerm.isEmpty()) {
			medicines = medicineService.searchingMedicineFromBuyer(searchTerm);
		} else {
			medicines = medicineService.getAllMedicineDetails();
		}
		model.addAttribute("userId", userId);
		model.addAttribute("searchTerm", searchTerm);
		model.addAttribute("medicines", medicines);
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("userEmail", user.getEmail());

		

		boolean disablePagination = medicines.size() <= 5;
		model.addAttribute("disablePagination", disablePagination);

		if (StringUtils.isEmpty(searchTerm)) {

			return listByPageInBuyer(1, searchTerm, model, userDetails);
		}

		return BUYER;
	}

	@GetMapping("/" + BUYER + "/page/{pageNo}")
	public String listByPageInBuyer(@PathVariable("pageNo") int currentPage,
			@RequestParam(required = false) String searchTerm, Model model,
			@AuthenticationPrincipal UserDetails userDetails) {
		User user = userService.findUserByEmail(userDetails.getUsername());
		Page<MedicineDetail> page;

		if (Objects.nonNull(searchTerm) && !searchTerm.isEmpty()) {
			page = medicineService.searchMedicineByPages(searchTerm, currentPage);
		} else {
			page = medicineService.listAll(currentPage);
		}
		List<MedicineDetail> list = page.getContent();
		model.addAttribute("userId", user.getUserId());
		model.addAttribute("totalElements", page.getTotalElements());
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("medicines", list);
		model.addAttribute("userName", user.getUserName());
		model.addAttribute("userEmail", user.getEmail());


		return BUYER;
	}
}