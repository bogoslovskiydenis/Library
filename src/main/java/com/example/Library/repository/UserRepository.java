package com.example.Library.repository;

import com.example.Library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long>{
    User findByUsername(String username);
}
