package com.mariana.dscommerce1.services;
import com.mariana.dscommerce1.dto.OrderDTO;
import com.mariana.dscommerce1.dto.OrderItemDTO;
import com.mariana.dscommerce1.entities.*;
import com.mariana.dscommerce1.repositories.OrderItemRepository;
import com.mariana.dscommerce1.repositories.OrderRepository;
import com.mariana.dscommerce1.repositories.ProductRepository;
import com.mariana.dscommerce1.services.exceptions.ResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order  = repository.findById(id).orElseThrow(
                ()-> new ResourcesNotFoundException("Recurso não encontrado"));
           authService.validateSelfOrAdmin(order.getClient().getId());
        return new OrderDTO(order);
    }

    @Transactional
    public OrderDTO insert(OrderDTO orderDTO) {
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

         User user = userService.authenticated();
		 order.setClient(user);

        for (OrderItemDTO itemDto: orderDTO.getItems()){
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order,product, itemDto.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }
        repository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);

    }
}
