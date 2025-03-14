package com.ms.account_transaction.mapper.impl;

import com.ms.account_transaction.api.controller.dto.TransactionDto;
import com.ms.account_transaction.domain.model.entity.Transaction;
import com.ms.account_transaction.mapper.TransactionMapper;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file :  TransactionMapperImpl
 * @since : 11/3/2025, mar
 **/

@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public TransactionDto toDto(Transaction account) {
        if (account == null) {
            return null;
        }
        TransactionDto transactionDto=new TransactionDto();
        transactionDto.setTransactionType(account.getTransactionType());
        transactionDto.setAmount(account.getAmount());
        transactionDto.setBalance(account.getBalance());
        transactionDto.setDate(account.getDate());
        transactionDto.setDescription(account.getDescription());
        return  transactionDto;
    }
}
