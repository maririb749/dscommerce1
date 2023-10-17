package com.mariana.dscommerce1.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mariana.dscommerce1.entities.User;
import com.mariana.dscommerce1.projection.UserDetailsProjection;
import com.mariana.dscommerce1.repositories.UserRepository;
import com.mariana.dscommerce1.services.UserService;
import com.mariana.dscommerce1.tests.UserDetailsFactory;
import com.mariana.dscommerce1.tests.UserFactory;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

	@InjectMocks
	private UserService service;

	@Mock
	private UserRepository repository;

	private String existingUserName, nonExistingUserName;
	private User user;
	private List<UserDetailsProjection> userDetails;

	@BeforeEach
	void setUp() throws Exception {
		existingUserName = "maria@gmail.com";
		nonExistingUserName = "user@gmail";
		user = UserFactory.createCustomClientUser(1L, existingUserName);
		userDetails = UserDetailsFactory.createCustomAdmintClientUser(existingUserName);

		Mockito.when(repository.searchUsernameWithRolesByEmail(existingUserName)).thenReturn(userDetails);
		Mockito.when(repository.searchUsernameWithRolesByEmail(nonExistingUserName)).thenReturn(new ArrayList<>());

	}

	@Test
	public void loadUserByUserNameShouldReturnUserDetailsWhenUserExists() {

		UserDetails result = service.loadUserByUsername(existingUserName);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getUsername(), existingUserName);

	}

	@Test
	public void loadUserByUserNameShouldThrowUsernameNotFoundExceptionsWhenUserDoesNotExist() {

		Assertions.assertThrows(UsernameNotFoundException.class, () -> {
			service.loadUserByUsername(nonExistingUserName);

		});

	}

}
