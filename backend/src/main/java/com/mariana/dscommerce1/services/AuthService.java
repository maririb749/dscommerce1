package com.mariana.dscommerce1.services;

import com.mariana.dscommerce1.entities.User;
import com.mariana.dscommerce1.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

	@Autowired
	private UserService userService;

	public void validateSelfOrAdmin(long userId) {
		User me = this.userService.autheticated();
		if (!me.hasRole("ROLE_ADMIN")) {
			return;
		}
		if (!me.getId().equals(userId)) {
			throw new ForbiddenException("Access denied. Should be self or admin");
		}

	}
}
