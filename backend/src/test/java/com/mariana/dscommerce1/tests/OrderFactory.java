package com.mariana.dscommerce1.tests;

import java.time.Instant;

import com.mariana.dscommerce1.entities.Order;
import com.mariana.dscommerce1.entities.OrderItem;
import com.mariana.dscommerce1.entities.OrderStatus;
import com.mariana.dscommerce1.entities.Payment;
import com.mariana.dscommerce1.entities.Product;
import com.mariana.dscommerce1.entities.User;

public class OrderFactory {

	
	public static Order createOrder(User Client) {
		Order order = new Order(1L, Instant.now(), OrderStatus.WAITING_PAYMENT, Client, new Payment());
		
		Product product = ProductFactory.createProduct();
		OrderItem orderItem = new OrderItem(order,product,2,10.0);
		order.getItems().add(orderItem);
		return order;
		
	}
}
