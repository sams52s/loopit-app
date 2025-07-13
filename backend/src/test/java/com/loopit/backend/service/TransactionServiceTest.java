package com.loopit.backend.service;

import com.loopit.backend.dto.TransactionDto;
import com.loopit.backend.model.Product;
import com.loopit.backend.model.Transaction;
import com.loopit.backend.model.User;
import com.loopit.backend.repository.ProductRepository;
import com.loopit.backend.repository.TransactionRepository;
import com.loopit.backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {

    private TransactionService transactionService;
    private TransactionRepository transactionRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        productRepository = mock(ProductRepository.class);
        userRepository = mock(UserRepository.class);
        transactionService = new TransactionService(transactionRepository, productRepository, userRepository);
    }

    @Test
    public void testCreateTransaction() {
        UUID productId = UUID.randomUUID();
        UUID renterId = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();

        Product product = Product.builder().id(productId).owner(User.builder().id(ownerId).build()).build();
        User renter = User.builder().id(renterId).build();

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(userRepository.findById(renterId)).thenReturn(Optional.of(renter));

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID())
                .product(product)
                .renter(renter)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(2))
                .totalPrice(Double.valueOf(200))
                .isActive(true)
                .build();

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        TransactionDto dto = new TransactionDto(null, productId.toString(), renterId.toString(), null,
                null, transaction.getStartDate().toString(), transaction.getEndDate().toString(), transaction.getTotalPrice());

        TransactionDto result = transactionService.createTransaction(dto);

        assertEquals(productId.toString(), result.getProductId());
        assertEquals(renterId.toString(), result.getRenterId());
        assertEquals(ownerId.toString(), result.getOwnerId());
        assertEquals("active", result.getStatus());
        assertEquals(transaction.getTotalPrice(), result.getTotalPrice());
    }

    @Test
    public void testGetAllTransactions() {
        UUID productId = UUID.randomUUID();
        UUID renterId = UUID.randomUUID();
        UUID ownerId = UUID.randomUUID();

        Product product = Product.builder().id(productId).owner(User.builder().id(ownerId).build()).build();
        User renter = User.builder().id(renterId).build();

        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID())
                .product(product)
                .renter(renter)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now().plusDays(2))
                .totalPrice(Double.valueOf(200))
                .isActive(true)
                .build();

        when(transactionRepository.findAll()).thenReturn(Collections.singletonList(transaction));

        var result = transactionService.getAllTransactions();

        assertEquals(1, result.size());
        assertEquals(productId.toString(), result.get(0).getProductId());
        assertEquals(renterId.toString(), result.get(0).getRenterId());
        assertEquals(ownerId.toString(), result.get(0).getOwnerId());
        assertEquals("active", result.get(0).getStatus());
    }
}
