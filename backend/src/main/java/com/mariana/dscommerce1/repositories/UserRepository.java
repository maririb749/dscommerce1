package com.mariana.dscommerce1.repositories;

import com.mariana.dscommerce1.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);



}
