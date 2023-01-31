package com.mariana.dscommerce1.controllers;

import com.mariana.dscommerce1.dto.ProductDTO;
import com.mariana.dscommerce1.entities.Product;
import com.mariana.dscommerce1.repositories.ProductRepository;
import com.mariana.dscommerce1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService service;
    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable Long id){
        ProductDTO dto = service.findById(id);
        return dto;
    }
}
