package com.ms.account_transaction.domain.service.impl;

import com.ms.account_transaction.api.controller.dto.AccountDto;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.domain.model.enums.Status;
import com.ms.account_transaction.domain.repository.AccountRepository;
import com.ms.account_transaction.domain.repository.TransactionRepository;
import com.ms.account_transaction.exception.GeneralException;
import com.ms.account_transaction.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author : Freddy Torres
 * file :  AccountServiceImplTest
 * @since : 13/3/2025, jue
 **/

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    private final static Long ACCOUNT_ID = 1L;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private AccountMapper accountMapper;
    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    AccountDto accountDto = new AccountDto();
    Account account = new Account();

    @Test
    void getAllAccountNull() {
        // When
        when(accountRepository.findAll()).thenReturn(List.of());
        // Then
        assertThrows(GeneralException.class, () -> accountServiceImpl.getAllAccount());
    }

    @Test
    void getAllAccountNotNull() {
        // Given
        List<Account> accountList = Arrays.asList(account, new Account());
        // When
        when(accountRepository.findAll()).thenReturn(accountList);
        when(accountMapper.toDto(any(Account.class))).thenReturn(accountDto, new AccountDto());
        List<AccountDto> result = accountServiceImpl.getAllAccount();
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getAccountById() {
        // When
        when(accountRepository.findByAccountId(ACCOUNT_ID)).thenReturn(List.of());
        // Then
        assertThrows(GeneralException.class, () -> accountServiceImpl.getAccountById(ACCOUNT_ID));
    }

    @Test
    void getAccountByIdNotNull() {
        // Given
        List<Account> accountList = Arrays.asList(account, new Account());
        // When
        when(accountRepository.findByAccountId(ACCOUNT_ID)).thenReturn(accountList);
        when(accountMapper.toDto(any(Account.class))).thenReturn(accountDto, new AccountDto());
        List<AccountDto> result = accountServiceImpl.getAccountById(ACCOUNT_ID);
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void deleteClient() {
        // When
        when(accountRepository.existsByAccountId(ACCOUNT_ID)).thenReturn(true);
        accountServiceImpl.deleteAccount(ACCOUNT_ID);
        // Then
        verify(accountRepository, times(1)).deleteByAccountId(ACCOUNT_ID);
    }

    @Test
    void deleteClientNoFound() {
        // When
        when(accountRepository.existsByAccountId(ACCOUNT_ID)).thenReturn(false);
        // Then
        assertThrows(GeneralException.class, () -> accountServiceImpl.deleteAccount(ACCOUNT_ID));
    }

    @Test
    void createAccount() {
        // Given
        Account account = new Account();
        account.setStatus(Status.TRUE);
        // When
        when(accountRepository.save(any(Account.class))).thenReturn(account);
        Account result = accountServiceImpl.createAccount(account);
        // Then
        assertNotNull(result);
        assertEquals(Status.TRUE, result.getStatus());
    }

}
