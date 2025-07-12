

package com.loopit.backend.service;

import com.loopit.backend.dto.TransactionDto;
import com.loopit.backend.model.Product;
import com.loopit.backend.model.Transaction;
import com.loopit.backend.model.User;
import com.loopit.backend.repository.ProductRepository;
import com.loopit.backend.repository.TransactionRepository;
import com.loopit.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public TransactionDto createTransaction(TransactionDto dto) {
        Product product = productRepository.findById(UUID.fromString(dto.getProductId()))
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        User renter = (User) userRepository.findById(UUID.fromString(dto.getRenterId()))
                .orElseThrow(() -> new IllegalArgumentException("Renter not found"));

        Transaction transaction = Transaction.builder()
                .product(product)
                .renter(renter)
                .startDate(LocalDateTime.parse(dto.getStartDate()))
                .endDate(LocalDateTime.parse(dto.getEndDate()))
                .totalPrice(dto.getTotalPrice())
                .isActive(true)
                .build();

        Transaction saved = transactionRepository.save(transaction);
        return new TransactionDto(
                saved.getId().toString(),
                saved.getProduct().getId().toString(),
                saved.getRenter().getId().toString(),
                saved.getProduct().getOwner().getId().toString(),
                saved.isActive() ? "active" : "inactive",
                saved.getStartDate().toString(),
                saved.getEndDate().toString(),
                saved.getTotalPrice()
        );
    }

    public List<TransactionDto> getAllTransactions() {
        return transactionRepository.findAll().stream().map(transaction ->
                new TransactionDto(
                        transaction.getId().toString(),
                        transaction.getProduct().getId().toString(),
                        transaction.getRenter().getId().toString(),
                        transaction.getProduct().getOwner().getId().toString(),
                        transaction.isActive() ? "active" : "inactive",
                        transaction.getStartDate().toString(),
                        transaction.getEndDate().toString(),
                        transaction.getTotalPrice()
                )
        ).collect(Collectors.toList());
    }
}