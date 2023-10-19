package com.mariana.dscommerce1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mariana.dscommerce1.dto.UserDTO;
import com.mariana.dscommerce1.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;
    

	public UserController(UserService service) {
		this.service = service;
	}

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getLoggedUser(){
    	final UserDTO dto = this.service.getLoggedUser();
        return ResponseEntity.ok(dto);
    }

}
