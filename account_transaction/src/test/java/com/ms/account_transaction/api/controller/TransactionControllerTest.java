package com.ms.account_transaction.api.controller;

import com.ms.account_transaction.api.controller.dto.TransactionDto;
import com.ms.account_transaction.domain.model.entity.Transaction;
import com.ms.account_transaction.domain.service.TransactionService;
import com.ms.account_transaction.dto.response.ApiResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author : Freddy Torres
 * file :  TransactionControllerTest
 * @since : 12/3/2025, mié
 **/

class TransactionControllerTest {

    private final static Long ACCOUND_ID = 1L;

    @Mock
    private AutoCloseable closeable;
    @Mock
    private TransactionService transactionService;

    private Transaction transaction;
    @InjectMocks
    private TransactionController transactionController;

    List<TransactionDto> transactionDtoList;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        transaction= new Transaction();
        transactionDtoList= new ArrayList<>();
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void getPing_OK() {
        // When
        ResponseEntity<String> responseEntity = transactionController.ping();
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void createAccount()  {
       //When
        ResponseEntity<ApiResponse<TransactionDto>> responseEntity = transactionController.createAccount(transaction);
        //Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }
    @Test
    void getTransactionsByAccount() {
        // Given
        when(transactionService.getTransactionsByAccount(ACCOUND_ID)).thenReturn(transactionDtoList);
        // When
        ResponseEntity<ApiResponse<List<TransactionDto>>> responseEntity = transactionController.getTransactionsByAccount(ACCOUND_ID);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    void getTransactionsByClientId() {
        // Given
        when(transactionService.getTransactionsByClientId(ACCOUND_ID)).thenReturn(transactionDtoList);
        // When
        ResponseEntity<ApiResponse<List<TransactionDto>>> responseEntity = transactionController.getTransactionsByClientId(ACCOUND_ID);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
