package com.loopit.backend.repository;

import com.loopit.backend.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByCategory_Name(String categoryName);

    List<Product> findByOwnerId(UUID ownerId);

    List<Product> findByIsAvailableTrue();

    List<Product> findByCategoryId(UUID categoryId);

    List<Product> findByCategoryIdAndIsAvailableTrue(UUID categoryId);

    List<Product> findByOwnerIdAndIsAvailableTrue(UUID ownerId);

    List<Product> findByUserId(UUID userId);
}
