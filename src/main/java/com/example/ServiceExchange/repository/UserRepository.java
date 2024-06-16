package com.example.ServiceExchange.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.ServiceExchange.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
