package com.loopit.backend.repository;

import com.loopit.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    List<User> findAllByOrderByIsDeletedAscCreatedAtDesc();

    Optional<Object> findById(UUID uuid);
}
