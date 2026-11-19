package com.carrerpilot.user_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.carrerpilot.user_service.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
 
    User findByEmail(String email);

    boolean existsByEmail(String email);
}
