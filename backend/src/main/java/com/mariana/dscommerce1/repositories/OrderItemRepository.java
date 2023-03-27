package com.mariana.dscommerce1.repositories;


import com.mariana.dscommerce1.entities.OrderItem;
import com.mariana.dscommerce1.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {



}
