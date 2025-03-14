package com.ms.account_transaction.domain.service;

import com.ms.account_transaction.api.controller.dto.TransactionDto;
import com.ms.account_transaction.domain.model.entity.Transaction;

import java.util.List;

/**
 * @author : Freddy Torres
 * file :  TransactionService
 * @since : 11/3/2025, mar
 **/

public interface TransactionService {
    TransactionDto createTransaction(Transaction transaction);
    List<TransactionDto> getTransactionsByAccount(Long accountId);
    List<TransactionDto> getTransactionsByClientId(Long clientId);
}
