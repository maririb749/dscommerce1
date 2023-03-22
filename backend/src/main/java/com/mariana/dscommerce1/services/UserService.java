package com.mariana.dscommerce1.services;

import com.mariana.dscommerce1.entities.User;
import com.mariana.dscommerce1.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository Repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Email not found");
        }
        return user;
    }
}