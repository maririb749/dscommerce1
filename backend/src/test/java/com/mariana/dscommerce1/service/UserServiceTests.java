package com.mariana.dscommerce1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mariana.dscommerce1.dto.UserDTO;
import com.mariana.dscommerce1.entities.User;
import com.mariana.dscommerce1.projection.UserDetailsProjection;
import com.mariana.dscommerce1.repositories.UserRepository;
import com.mariana.dscommerce1.services.UserService;
import com.mariana.dscommerce1.tests.UserDetailsFactory;
import com.mariana.dscommerce1.tests.UserFactory;
import com.mariana.dscommerce1.utils.CustomUserUtil;

@ExtendWith(SpringExtension.class)
public class UserServiceTests {

	@InjectMocks
	private UserService service;

	@Mock
	private UserRepository repository;

	@Mock
	private CustomUserUtil userUtil;

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

		Mockito.when(repository.findByEmail(existingUserName)).thenReturn(Optional.of(user));

		Mockito.when(repository.findByEmail(nonExistingUserName)).thenReturn(Optional.empty());

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

	@Test
	public void authenticatedShouldReturnUserWhenUserExists() {
		Mockito.when(userUtil.getLoggedUsername()).thenReturn(existingUserName);

		User result = service.autheticated();

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getUsername(), existingUserName);
	}

	@Test
	public void authenticatedShowilThrowUsernameNotFoudExceptionWhenUseDoesNotExists() {
		Mockito.doThrow(ClassCastException.class).when(userUtil).getLoggedUsername();
		Assertions.assertThrows(UsernameNotFoundException.class, () -> {
			service.autheticated();
		});

	}

	@Test
	public void getMeShouldReturnUserDTOWhenUserAuthenticated() {
		
		UserService SpyUserService = Mockito.spy(service);
		Mockito.doReturn(user).when(SpyUserService).autheticated();
		
		UserDTO result = SpyUserService.getMe();
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getEmail(), existingUserName);

	}
	
	@Test
	public void getMeShouldThowUsernameNotFoundExceptionWhenUserNotAuthenticated() {
		
		UserService SpyUserService = Mockito.spy(service);
		
		Mockito.doThrow(UsernameNotFoundException.class).when(SpyUserService).autheticated();
		
		Assertions.assertThrows(UsernameNotFoundException.class, () -> {
		UserDTO result = SpyUserService.getMe();
	
		});
	}
	
}
