package com.mariana.dscommerce1.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mariana.dscommerce1.dto.OrderDTO;
import com.mariana.dscommerce1.entities.Order;
import com.mariana.dscommerce1.entities.OrderItem;
import com.mariana.dscommerce1.entities.Product;
import com.mariana.dscommerce1.entities.User;
import com.mariana.dscommerce1.repositories.OrderItemRepository;
import com.mariana.dscommerce1.repositories.OrderRepository;
import com.mariana.dscommerce1.repositories.ProductRepository;
import com.mariana.dscommerce1.services.AuthService;
import com.mariana.dscommerce1.services.OrderService;
import com.mariana.dscommerce1.services.UserService;
import com.mariana.dscommerce1.services.exceptions.ForbiddenException;
import com.mariana.dscommerce1.services.exceptions.ResourcesNotFoundException;
import com.mariana.dscommerce1.tests.OrderFactory;
import com.mariana.dscommerce1.tests.ProductFactory;
import com.mariana.dscommerce1.tests.UserFactory;
import javax.persistence.EntityNotFoundException;

@SuppressWarnings("unused")
@ExtendWith(SpringExtension.class)
public class OrderServiceTests {

	@InjectMocks
	private OrderService service;

	@Mock
	private OrderRepository repository;

	@Mock
	private OrderItemRepository orderItemRepository;

	@Mock
	private UserService userService;

	@Mock
	private AuthService authService;

	@Mock
	private ProductRepository productRepository;

	@SuppressWarnings("unused")
	private Long exisistingProductId, nonExistingProductId;
	private Long existingOrderId, nonExistingOrderId;
	private OrderDTO orderDTO;
	private Order order;
	private User admin, client;
	private Product product;

	@BeforeEach
	void SetUp() throws Exception {

		existingOrderId = 1L;

		nonExistingOrderId = 2L;

		exisistingProductId = 1L;
		nonExistingProductId = 2L;

		admin = UserFactory.createCustomAdmintUser(1L, "Jef");
		client = UserFactory.createCustomClientUser(2L, "Bob");

		order = OrderFactory.createOrder(client);
		orderDTO = new OrderDTO(order);
		product = ProductFactory.createProduct();

		Mockito.when(repository.findById(existingOrderId)).thenReturn(Optional.of(order));

		Mockito.when(repository.findById(nonExistingOrderId)).thenReturn(Optional.empty());

		Mockito.when(productRepository.getReferenceById(exisistingProductId)).thenReturn(product);
		Mockito.when(productRepository.getReferenceById(nonExistingProductId)).thenThrow(EntityNotFoundException.class);

		Mockito.when(repository.save(any())).thenReturn(order);

		Mockito.when(orderItemRepository.saveAll(any())).thenReturn(new ArrayList<>(order.getItems()));

	}

	@Test
	public void findByIdShouldReturnOrderDTOWhenExistsAndAdminLogged() {

		Mockito.doNothing().when(authService).validateSelfUserOrAdmin(Mockito.any());

		OrderDTO result = service.findById(existingOrderId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingOrderId);

	}

	@Test
	public void findByIdShouldReturnOrderDTOWhenIdExistsAndSelfClientLogged() {

		Mockito.doNothing().when(authService).validateSelfUserOrAdmin(Mockito.any());
		OrderDTO result = service.findById(existingOrderId);

		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingOrderId);

	}

	@Test
	public void findByIdShouldthrowsForbiddenExceptionWhenIdExistsAndSelfClientLogged() {

		Mockito.doThrow(ForbiddenException.class).when(authService).validateSelfUserOrAdmin(Mockito.any());

		Assertions.assertThrows(ForbiddenException.class, () -> {
			OrderDTO result = service.findById(existingOrderId);

		});

	}

	@Test
	public void findByIdShouldThrowsResourseNotFounExceptionWhenIdDoesNotExists() {

		Mockito.doNothing().when(authService).validateSelfUserOrAdmin(Mockito.any());

		Assertions.assertThrows(ResourcesNotFoundException.class, () -> {
			OrderDTO result = service.findById(nonExistingOrderId);

		});

	}

	@Test
	public void insertShouldReturnOrderDTOWhenUserAdminLogged() {

		Mockito.when(userService.authenticated()).thenReturn(admin);

		OrderDTO result = service.insert(orderDTO);

		Assertions.assertNotNull(result);

	}

	@Test
	public void insertShouldReturnOrderDTOWhenUserClientLogged() {

		Mockito.when(userService.authenticated()).thenReturn(client);

		OrderDTO result = service.insert(orderDTO);

		Assertions.assertNotNull(result);

	}

	@Test
	public void insertShouldThrowsUserNameNotFoundExceptionWhenUserNotLogged() {

		Mockito.doThrow(UsernameNotFoundException.class).when(userService).authenticated();

		order.setClient(new User());
		orderDTO = new OrderDTO(order);

		
		Assertions.assertThrows(UsernameNotFoundException.class, () -> {
			
			final OrderDTO result = service.insert(orderDTO);
		});
	}
	

	@Test
	public void insertShouldThrowsEntityNotFoundExceptionWhenOrderProductDoesNotExists() {

		Mockito.when(userService.authenticated()).thenReturn(client);

		product.setId(nonExistingProductId);
		OrderItem orderItem = new OrderItem(order, product, 2, 10.0);
		order.getItems().add(orderItem);

		orderDTO = new OrderDTO(order);

		
			OrderDTO result = service.insert(orderDTO);
		
	}

}
