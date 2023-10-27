package com.mariana.dscommerce1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mariana.dscommerce1.entities.User;
import com.mariana.dscommerce1.services.exceptions.ForbiddenException;

@Service
public class AuthService {

	@Autowired
	private UserService userService;

	public void validateSelfUserOrAdmin(Long userId) {
		User me = userService.authenticated();
		if (me.hasRole("ROLE_ADMIN")) {
			return;
		}
		if (!me.getId().equals(userId)) {
			throw new ForbiddenException("Access denied. Should be self or admin");
		}
	}
}
