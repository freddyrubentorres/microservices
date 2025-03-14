package com.ms.account_transaction.mapper.impl;

import com.ms.account_transaction.api.controller.dto.TransactionDto;
import com.ms.account_transaction.domain.model.entity.Transaction;
import com.ms.account_transaction.domain.model.enums.TransactionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Freddy Torres
 * file :  TransactionMapperImplTest
 * @since : 12/3/2025, mié
 **/

class TransactionMapperImplTest {
    private TransactionMapperImpl transactionMapper;

    @BeforeEach
    public void setUp() {
        transactionMapper = new TransactionMapperImpl();
    }

    @Test
    public void testToDto_ValidTransaction() {
        // Given
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.DEBITO);
        transaction.setAmount(100.0);
        transaction.setBalance(200.0);
        transaction.setDate(new Date());
        transaction.setDescription("Deposit to account");
        // When
        TransactionDto transactionDto = transactionMapper.toDto(transaction);
        // Then
        assertNotNull(transactionDto);
        assertEquals(100.0, transactionDto.getAmount());
        assertEquals(200.0, transactionDto.getBalance());
    }

    @Test
    public void testToDto_NullTransaction() {
        // When
        TransactionDto transactionDto = transactionMapper.toDto(null);
        // Then
        assertNull(transactionDto);
    }
}
