package com.spring.lifecare.service;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * CustomSuccessHandler is an implementation of the Spring Security
 * AuthenticationSuccessHandler interface. It defines the behavior to handle
 * successful authentication, redirecting users to their respective pages based
 * on their roles.
 */
@Service
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

	/**
	 * Handles the authentication success by redirecting the user to their
	 * respective pages based on their roles.
	 *
	 * @param request        The HttpServletRequest object.
	 * @param response       The HttpServletResponse object.
	 * @param authentication The Authentication object representing the
	 *                       authenticated user.
	 * @throws IOException      If an I/O exception occurs while handling the
	 *                          successful authentication.
	 * @throws ServletException If a servlet exception occurs while handling the
	 *                          successful authentication.
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		var authorities = authentication.getAuthorities();
		var roles = authorities.stream().map(r -> r.getAuthority()).findFirst();

		if (roles.orElse("").equals("SELLER")) {
			response.sendRedirect("/seller");
		} else if (roles.orElse("").equals("BUYER")) {
			response.sendRedirect("/buyer");
		} else {
			response.sendRedirect("/error");
		}
	}
}