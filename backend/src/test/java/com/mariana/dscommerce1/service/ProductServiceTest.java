package com.mariana.dscommerce1.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mariana.dscommerce1.dto.ProductDTO;
import com.mariana.dscommerce1.dto.ProductMinDTO;
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
	private PageImpl<Product> page;

	@BeforeEach
	void setUp() throws Exception {
		existingProductId = 1L;
		nonExistingProductId = 2L;
		productName = "Playstation5";
		product = ProductFactory.createProduct(productName);
		page = new PageImpl<>(List.of(product));

		Mockito.when(repository.findById(existingProductId)).thenReturn(Optional.of(product));
		Mockito.when(repository.findById(nonExistingProductId)).thenReturn(Optional.empty());
		Mockito.when(repository.searchByName(any(), (Pageable) any())).thenReturn(page);

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

	@Test
	public void findAllShouldReturnPagedProductMinDTO() {
		Pageable pageable = PageRequest.of(0, 12);
		Page<ProductMinDTO> result = service.findAll(productName, pageable);
		Assertions.assertNotNull(result);
		Assertions.assertEquals(result.getSize(), 1);
		Assertions.assertEquals(result.iterator().next().getName(), productName);

	}

}
