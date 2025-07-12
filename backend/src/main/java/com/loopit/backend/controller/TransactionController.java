

package com.loopit.backend.controller;

import com.loopit.backend.dto.TransactionDto;
import com.loopit.backend.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @MutationMapping
    public TransactionDto createTransaction(@Argument TransactionDto input) {
        return transactionService.createTransaction(input);
    }

    @QueryMapping
    public List<TransactionDto> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
}