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

import com.mariana.dscommerce1.dto.ProductDTO;
import com.mariana.dscommerce1.entities.Product;
import com.mariana.dscommerce1.repositories.ProductRepository;
import com.mariana.dscommerce1.services.ProductService;
import com.mariana.dscommerce1.services.exceptions.ResourcesNotFoundException;
import com.mariana.dscommerce1.tests.ProductFactory;

@ExtendWith(SpringExtension.class)
public class ProductServiceTest {

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	@SuppressWarnings("unused")
	private long existingProductId, nonExistingProductId;
	private String productName;
	private Product product;

	@BeforeEach
	void setUp() throws Exception {
		existingProductId = 1L;
		nonExistingProductId = 2L;

		productName = "Playstation5";

		product = ProductFactory.createProduct(productName);

		Mockito.when(repository.findById(existingProductId)).thenReturn(Optional.of(product));
		Mockito.when(repository.findById(nonExistingProductId)).thenReturn(Optional.empty());

	}

	
	@Test
	public void findByIdShouldReturnProductDTOWhenIdExists() {
		
		ProductDTO result = service.findById(existingProductId);
		
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getId(), existingProductId);
		Assertions.assertEquals(result.getName(), product.getName());
	}
	@Test
	public void findByIdShouldReturnResourceNotFoundExceptionWhenIdDoesNotExists() {
		Assertions.assertThrows(ResourcesNotFoundException.class, () -> {
			service.findById(nonExistingProductId);
		});
		
	}

}
