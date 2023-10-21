package com.mariana.dscommerce1.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mariana.dscommerce1.dto.OrderDTO;
import com.mariana.dscommerce1.entities.Order;
import com.mariana.dscommerce1.entities.User;
import com.mariana.dscommerce1.repositories.OrderRepository;
import com.mariana.dscommerce1.services.AuthService;
import com.mariana.dscommerce1.services.OrderService;
import com.mariana.dscommerce1.services.exceptions.ForbiddenException;
import com.mariana.dscommerce1.services.exceptions.ResourcesNotFoundException;
import com.mariana.dscommerce1.tests.OrderFactory;
import com.mariana.dscommerce1.tests.UserFactory;

@ExtendWith(SpringExtension.class)
public class OrderServiceTests {

	@InjectMocks
	private OrderService service;

	@Mock
	private OrderRepository repository;

	@Mock
	private AuthService authService;

	private Long existingOrderId, nonExistingOrderId;

	@SuppressWarnings("unused")
	private OrderDTO orderDto;

	private Order order;

	@SuppressWarnings("unused")
	private User admin, client;

	@BeforeEach
	void SetUp() throws Exception {
		existingOrderId = 1L;
		nonExistingOrderId = 2L;

		admin = UserFactory.createCustomAdmintUser(1L, "Jef");
		client = UserFactory.createCustomClientUser(2L, "Bob");
		order = OrderFactory.createOrder(client);
		orderDto = new OrderDTO(order);

		Mockito.when(repository.findById(existingOrderId)).thenReturn(Optional.of(order));

		Mockito.when(repository.findById(nonExistingOrderId)).thenReturn(Optional.empty());

	}

	@Test
	public void findByIdShouldReturnOrderDTOWhenExistsAndAdminLogged() {

		Mockito.doNothing().when(authService).validateSelfOrAdmin(Mockito.any());

		OrderDTO result = service.findById(existingOrderId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingOrderId);

	}

	@Test
	public void findByIdShouldReturnOrderDTOWhenIdExistsAndSelfClientLogged() {

		Mockito.doNothing().when(authService).validateSelfOrAdmin(Mockito.any());
		OrderDTO result = service.findById(existingOrderId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingOrderId);

	}

	@Test
	public void findByIdShouldthrowsForbiddenExceptionWhenIdExistsAndSelfClientLogged() {

		Mockito.doThrow(ForbiddenException.class).when(authService).validateSelfOrAdmin(Mockito.any());

		Assertions.assertThrows(ForbiddenException.class, () -> {
			OrderDTO result = service.findById(existingOrderId);

		});

	}

	@Test
	public void findByIdShouldThrowsResourseNotFounExceptionWhenIdDoesNotExists() {

		Mockito.doNothing().when(authService).validateSelfOrAdmin(Mockito.any());

		Assertions.assertThrows(ResourcesNotFoundException.class, () -> {
			OrderDTO result = service.findById(nonExistingOrderId);

		});

	}
}
