package com.mariana.dscommerce1.controllers;

import com.mariana.dscommerce1.dto.ProductDTO;
import com.mariana.dscommerce1.dto.UserDTO;
import com.mariana.dscommerce1.services.ProductService;
import com.mariana.dscommerce1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe(){
        UserDTO dto = service.getMe();
        return ResponseEntity.ok(dto);
    }

}
