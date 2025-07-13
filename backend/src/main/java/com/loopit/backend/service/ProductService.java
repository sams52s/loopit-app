package com.loopit.backend.service;

import com.loopit.backend.dto.ProductDto;
import com.loopit.backend.model.Category;
import com.loopit.backend.model.Product;
import com.loopit.backend.model.User;
import com.loopit.backend.repository.CategoryRepository;
import com.loopit.backend.repository.ProductRepository;
import com.loopit.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    public ProductDto createProduct(ProductDto input) {
        Product product = new Product();
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setAvailable(input.getIsAvailable());

        User owner = (User) userRepository.findById(input.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        product.setOwner(owner);

        Category category = categoryRepository.findById(input.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        Product saved = productRepository.save(product);

        return new ProductDto(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getPrice(),
                saved.isAvailable(),
                saved.getOwner().getId(),
                saved.getCategory().getId()
        );
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductDto(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.isAvailable(),
                        p.getOwner().getId(),
                        p.getCategory().getId()
                )).collect(Collectors.toList());
    }
}
