package com.loopit.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class TransactionDto {
    private final String id;
    private final String productId;
    private final String renterId;
    private final String ownerId;
    private final String status;
    private final String startDate;
    private final String endDate;
    private final Double totalPrice;
}
