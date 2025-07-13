package com.loopit.backend.service;

import com.loopit.backend.dto.ProductDto;
import com.loopit.backend.model.Category;
import com.loopit.backend.model.Product;
import com.loopit.backend.model.User;
import com.loopit.backend.repository.CategoryRepository;
import com.loopit.backend.repository.ProductRepository;
import com.loopit.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductService productService;

    private User testUser;
    private Category testCategory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setId(UUID.randomUUID());

        testCategory = new Category();
        testCategory.setId(UUID.randomUUID());
    }

    @Test
    public void testCreateProduct() {
        ProductDto input = new ProductDto(null, "Test Product", "Test Description", Double.valueOf("10.00"), true, testUser.getId(), testCategory.getId());

        when(userRepository.findById(input.getOwnerId())).thenReturn(Optional.of(testUser));
        when(categoryRepository.findById(input.getCategoryId())).thenReturn(Optional.of(testCategory));

        Product savedProduct = new Product();
        savedProduct.setId(UUID.randomUUID());
        savedProduct.setName(input.getName());
        savedProduct.setDescription(input.getDescription());
        savedProduct.setPrice(input.getPrice());
        savedProduct.setAvailable(input.getIsAvailable());
        savedProduct.setOwner(testUser);
        savedProduct.setCategory(testCategory);

        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        ProductDto result = productService.createProduct(input);

        assertNotNull(result);
        assertEquals(input.getName(), result.getName());
        assertEquals(input.getDescription(), result.getDescription());
        assertEquals(input.getPrice(), result.getPrice());
    }

    @Test
    public void testGetAllProducts() {
        Product product = new Product();
        product.setId(UUID.randomUUID());
        product.setName("Product 1");
        product.setDescription("Description 1");
        product.setPrice(Double.valueOf("20.00"));
        product.setAvailable(true);
        product.setOwner(testUser);
        product.setCategory(testCategory);

        when(productRepository.findAll()).thenReturn(List.of(product));

        var results = productService.getAllProducts();

        assertEquals(1, results.size());
        assertEquals("Product 1", results.get(0).getName());
    }
}
