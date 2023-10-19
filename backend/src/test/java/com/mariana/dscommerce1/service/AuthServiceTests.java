package com.mariana.dscommerce1.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mariana.dscommerce1.entities.User;
import com.mariana.dscommerce1.services.AuthService;
import com.mariana.dscommerce1.services.UserService;
import com.mariana.dscommerce1.services.exceptions.ForbiddenException;
import com.mariana.dscommerce1.tests.UserFactory;



@SuppressWarnings("unused")
@ExtendWith(SpringExtension.class)
public class AuthServiceTests {

	@InjectMocks
	private AuthService service;

	@Mock
	private UserService userService;

	
	private User admin, selfClient, otherClient;

	@BeforeEach
	void setUp() throws Exception {
		admin = UserFactory.createAdminUser();
		selfClient = UserFactory.createCustomClientUser(1L, "Bob");
		otherClient = UserFactory.createCustomClientUser(2L, "Ana");

	}

	@Test
	public void validateSelfOrAdminShowldDoNothingWhenAdminLogged() {

		Mockito.when(userService.autheticated()).thenReturn(admin);

		Long userId = admin.getId();

		Assertions.assertDoesNotThrow(() -> {
			service.validateSelfOrAdmin(userId);

		});

	}

	@Test
	public void validateSelfOrAdminShowldDoNothingWhenSelfLogged() {

		Mockito.when(userService.autheticated()).thenReturn(selfClient);

		Long userId = selfClient.getId();

		Assertions.assertDoesNotThrow(() -> {
			service.validateSelfOrAdmin(userId);

		});
	}
	@Test
	public void validateSelfOrAdminThrowsForbiddenExceptionWhenClientOtherLogged() {

		Mockito.when(userService.autheticated()).thenReturn(selfClient);

		Long userId = otherClient.getId();
		
			Assertions.assertThrows(ForbiddenException.class, () -> {
			service.validateSelfOrAdmin(userId);

		});
	}
}
