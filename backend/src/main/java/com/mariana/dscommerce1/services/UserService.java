package com.mariana.dscommerce1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mariana.dscommerce1.dto.UserDTO;
import com.mariana.dscommerce1.entities.Role;
import com.mariana.dscommerce1.entities.User;
import com.mariana.dscommerce1.projection.UserDetailsProjection;
import com.mariana.dscommerce1.repositories.UserRepository;
import com.mariana.dscommerce1.utils.CustomUserUtil;

@Service
public class UserService implements UserDetailsService {

	private UserRepository repository;

	private CustomUserUtil customUserUtil;

	public UserService(UserRepository repository, CustomUserUtil customUserUtil) {
		this.repository = repository;
		this.customUserUtil = customUserUtil;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final List<UserDetailsProjection> result = this.repository.searchUsernameWithRolesByEmail(username);

		if (result.size() == 0)
			throw new UsernameNotFoundException("User not found");

		User user = new User();
		user.setEmail(username);
		user.setPassword(result.get(0).getPassword());

		for (UserDetailsProjection projection : result)
			user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));

		return user;
	}

	
	public User autheticated() {
		try {
			final String username = customUserUtil.getLoggedUsername();
			return this.repository.findByEmail(username).get();
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found");
		}
	}
	@Transactional(readOnly = true)
	public UserDTO getMe() {
		final User user = this.autheticated();
		return new UserDTO(user);
	}



}
