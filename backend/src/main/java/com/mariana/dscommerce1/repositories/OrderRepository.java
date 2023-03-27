package com.mariana.dscommerce1.repositories;

import com.mariana.dscommerce1.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {



}
