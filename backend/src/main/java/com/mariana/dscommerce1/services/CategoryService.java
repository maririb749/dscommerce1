package com.mariana.dscommerce1.services;

import com.mariana.dscommerce1.dto.CategoryDTO;
import com.mariana.dscommerce1.entities.Category;
import com.mariana.dscommerce1.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;


    @Transactional(readOnly = true)
        public List<CategoryDTO> findAll(){
        List<Category> result = repository.findAll();
        return result.stream().map(x -> new CategoryDTO(x)).toList();

    }

}






