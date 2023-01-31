package com.mariana.dscommerce1.repositories;

import com.mariana.dscommerce1.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product, Long> {
}
