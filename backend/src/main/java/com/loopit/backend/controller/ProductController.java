package com.loopit.backend.controller;

import com.loopit.backend.dto.ProductDto;
import com.loopit.backend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @MutationMapping
    public ProductDto createProduct(@Argument ProductDto input) {
        return productService.createProduct(input);
    }

    @QueryMapping
    public List<ProductDto> getProducts() {
        return productService.getAllProducts();
    }
}
