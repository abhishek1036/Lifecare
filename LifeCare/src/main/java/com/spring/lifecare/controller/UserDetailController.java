package com.spring.lifecare.controller;

import java.util.Objects;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.lifecare.entites.User;
import com.spring.lifecare.exception.ServiceException;
import com.spring.lifecare.model.UserModel;
import com.spring.lifecare.service.UserService;



@Controller
public class UserDetailController {
	private static final String REGISTRATION = "registration";
	private static final String LOGIN = "login";
	private static final String MESSAGE = "message";
	private static final String REDIRECT = "redirect:/";
	private static final String REGISTRATION_SUCCESS = "registrationSuccess";
	private UserService userService;

	public UserDetailController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/" + REGISTRATION)
	public String handlingRegistration(@ModelAttribute("user") UserModel usersModel, Model model,
			RedirectAttributes redirectAttributes,@RequestParam(value = "sosCardDetail", required = false) String sosCardDetail) {
		try {
			User user = userService.findUserByEmail(usersModel.getEmail());

			if (Objects.isNull(user)) {
				return processUserRegistration(usersModel, model, redirectAttributes);
			} else {
				model.addAttribute(MESSAGE, "User with email Id already exists");
				return REGISTRATION;
			}
		} catch (ServiceException e) {
			model.addAttribute(MESSAGE, "Error handling registration: " + e.getMessage());

			return REGISTRATION;
		}
	}

	private String processUserRegistration(UserModel usersModel, Model model, RedirectAttributes redirectAttributes) {
		if (usersModel.getPassword().equals(usersModel.getConfirmPassword())) {

				return handleRegistrationWithoutSOS(usersModel, redirectAttributes);
			
		} else {
			model.addAttribute(MESSAGE, "Password and Confirm Password Doesn't match");
			return REGISTRATION;
		}
	}

	private String handleRegistrationWithoutSOS(UserModel usersModel, RedirectAttributes redirectAttributes) {
		userService.createUserDetail(usersModel.getUsersDetail());
		redirectAttributes.addFlashAttribute(REGISTRATION_SUCCESS, true);

		return REDIRECT + LOGIN;
	}

	@GetMapping("/" + REGISTRATION)
	public String getRegisterForm(@ModelAttribute("user") User users) {
		return REGISTRATION;
	}

	@GetMapping("/" + LOGIN)
	public String login(Model model) {
		if (model.containsAttribute(REGISTRATION_SUCCESS)) {
			model.addAttribute("registrationSuccessMessage", "Registration successful! Please login.");
		}

		return LOGIN;
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}
}