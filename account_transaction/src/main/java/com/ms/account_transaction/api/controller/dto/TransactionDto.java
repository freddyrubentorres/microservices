package com.ms.account_transaction.api.controller.dto;

import com.ms.account_transaction.domain.model.enums.TransactionType;
import lombok.*;

import java.util.Date;

/**
 * @author : Freddy Torres
 * file :  TransactionDto
 * @since : 11/3/2025, mar
 **/

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private String description;
    private Date date;
    private TransactionType transactionType;
    private Double amount;
    private Double balance;
}
