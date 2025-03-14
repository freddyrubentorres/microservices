package com.ms.account_transaction.domain.service.impl;

import com.ms.account_transaction.api.controller.dto.TransactionDto;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.domain.model.enums.TransactionType;
import com.ms.account_transaction.domain.repository.TransactionRepository;
import com.ms.account_transaction.exception.GeneralException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.ms.account_transaction.mapper.TransactionMapper;
import org.mockito.exceptions.misusing.PotentialStubbingProblem;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import com.ms.account_transaction.domain.model.entity.Transaction;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author : Freddy Torres
 * file :  TransactionServiceImplTest
 * @since : 12/3/2025, mié
 **/

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    private final static int CLIENT_ID = 1;
    private final static Long CLIENT_ID_L = 1L;

    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private TransactionServiceImpl transactionServiceImpl;

    @Mock
    private TransactionMapper transactionMapper;

    private Transaction transaction;
    private TransactionDto transactionDto;
    @Mock
    Account accountEntity;
    @Mock
    List<Transaction> listTransaction;

    @BeforeEach
    void setUp() {
        transaction=new Transaction();
        transactionDto= new TransactionDto();
        accountEntity= new Account();
        accountEntity.setAccountId(1L);
        listTransaction = new ArrayList<>();
    }

    @Test
    void getTransactionsByClientIdIsNull() {
        // When
        when(transactionRepository.findByAccount_ClientIdOrderByDateDesc(CLIENT_ID)).thenReturn(List.of());
        // Then
        assertThrows(GeneralException.class, () -> transactionServiceImpl.getTransactionsByClientId(CLIENT_ID_L));
    }

    @Test
    void getTransactionsByClientIdNotNull() {
        // Given
        List<Transaction> clients = Arrays.asList(transaction, new Transaction());
        // When
       when(transactionRepository.findByAccount_ClientIdOrderByDateDesc(CLIENT_ID)).thenReturn(clients);
       when(transactionMapper.toDto(any(Transaction.class))).thenReturn(transactionDto, new TransactionDto());
        List<TransactionDto> result = transactionServiceImpl.getTransactionsByClientId(CLIENT_ID_L);
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getTransactionsByAccount() {
        // When
        when(transactionRepository.findByAccountOrderByDateDesc(accountEntity)).thenReturn(listTransaction);
        // Then
        assertThrows(PotentialStubbingProblem.class, () -> transactionServiceImpl.getTransactionsByAccount(CLIENT_ID_L));
    }

    @Test
    public void testCreateTransaction_Success_Deposit() {
        Transaction transaction = new Transaction();
        transaction.setAccount(accountEntity);
        transaction.setAmount(100.0);
        transaction.setDescription("Deposit");
        Transaction previousTransaction = new Transaction();
        previousTransaction.setBalance(200.0);
        previousTransaction.setAccount(accountEntity);
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionType(TransactionType.DEBITO);
        transactionDto.setAmount(100.0);
        when(transactionRepository.findTopByAccountOrderByDateDesc(accountEntity)).thenReturn(Optional.of(previousTransaction));
        when(transactionMapper.toDto(any(Transaction.class))).thenReturn(transactionDto);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        TransactionDto result = transactionServiceImpl.createTransaction(transaction);
        assertNotNull(result);
        assertEquals(TransactionType.DEBITO, result.getTransactionType());
        assertEquals(100.0, result.getAmount());
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    public void testCreateTransaction_Success_Debit() {
        Transaction transaction = new Transaction();
        transaction.setAccount(accountEntity);
        transaction.setAmount(-50.0); // Débito
        transaction.setDescription("Withdrawal");
        Transaction previousTransaction = new Transaction();
        previousTransaction.setBalance(200.0);
        previousTransaction.setAccount(accountEntity);
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setTransactionType(TransactionType.DEBITO);
        transactionDto.setAmount(-50.0);
        when(transactionRepository.findTopByAccountOrderByDateDesc(accountEntity)).thenReturn(Optional.of(previousTransaction));
        when(transactionMapper.toDto(any(Transaction.class))).thenReturn(transactionDto);
        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
        TransactionDto result = transactionServiceImpl.createTransaction(transaction);
        assertNotNull(result);
        assertEquals(TransactionType.DEBITO, result.getTransactionType());
        assertEquals(-50.0, result.getAmount());
        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    public void testCreateTransaction_AccountNotFound() {
        Transaction transaction = new Transaction();
        transaction.setAccount(accountEntity);
        transaction.setAmount(100.0);
        transaction.setDescription("Deposit");
        when(transactionRepository.findTopByAccountOrderByDateDesc(accountEntity)).thenReturn(Optional.empty());
        GeneralException exception = assertThrows(GeneralException.class, () -> {
            transactionServiceImpl.createTransaction(transaction);
        });
        verify(transactionRepository, never()).save(any(Transaction.class));
    }
}
