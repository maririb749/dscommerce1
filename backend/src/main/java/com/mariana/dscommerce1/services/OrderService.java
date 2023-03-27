package com.mariana.dscommerce1.services;
import com.mariana.dscommerce1.dto.OrderDTO;
import com.mariana.dscommerce1.entities.Order;
import com.mariana.dscommerce1.repositories.OrderRepository;
import com.mariana.dscommerce1.services.exceptions.ResourcesNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order  = repository.findById(id).orElseThrow(
                ()-> new ResourcesNotFoundException("Recurso não encontrado"));
        return new OrderDTO(order);
    }
}
