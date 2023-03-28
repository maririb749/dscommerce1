package com.mariana.dscommerce1.controllers;

import com.mariana.dscommerce1.dto.CategoryDTO;
import com.mariana.dscommerce1.dto.ProductDTO;
import com.mariana.dscommerce1.dto.ProductMinDTO;
import com.mariana.dscommerce1.services.CategoryService;
import com.mariana.dscommerce1.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll(){
              List<CategoryDTO> list = service.findAll();
               return ResponseEntity.ok(list);
    }

}
