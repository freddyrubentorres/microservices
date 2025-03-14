package com.ms.account_transaction.api.controller;

import com.ms.account_transaction.api.controller.dto.AccountDto;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.domain.service.AccountService;
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
 * file :  AccountControllerTest
 * @since : 13/3/2025, jue
 **/

class AccountControllerTest {
    @Mock
    private AutoCloseable closeable;

    private final static Long ACCOUND_ID = 1L;

    @InjectMocks
    private AccountController accountController;
    @Mock
    private AccountService accountService;
    List<AccountDto> accountDto = new ArrayList<>();
    Account account = new Account();

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void getPing_OK() {
        // When
        ResponseEntity<String> responseEntity = accountController.ping();
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAllAccounts() {
        // Given
        when(accountService.getAllAccount()).thenReturn(accountDto);
        // When
        ResponseEntity<ApiResponse<List<AccountDto>>> responseEntity = accountController.getAllAccounts();
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAccountById() {
        // Given
        when(accountService.getAccountById(ACCOUND_ID)).thenReturn(accountDto);
        // When
        ResponseEntity<ApiResponse<List<AccountDto>>> responseEntity = accountController.getAccountById(ACCOUND_ID);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void createAccount() {
        // Given
        when(accountService.createAccount(account)).thenReturn(account);
        // When
        ResponseEntity<ApiResponse<Account>> responseEntity = accountController.createAccount(account);
        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void deleteAccount() {
        // When
        ResponseEntity<ApiResponse<Void>> responseEntity = accountController.deleteAccount(ACCOUND_ID);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
