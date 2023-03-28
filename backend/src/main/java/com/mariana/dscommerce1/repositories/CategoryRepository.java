package com.mariana.dscommerce1.repositories;

import com.mariana.dscommerce1.entities.Category;
import com.mariana.dscommerce1.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {




}
