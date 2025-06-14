package com.auth.user_service.repository;

import com.auth.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
    Optional<User> findByEmail(String email);
    List<User> findByIsDeletedFalse();
}
