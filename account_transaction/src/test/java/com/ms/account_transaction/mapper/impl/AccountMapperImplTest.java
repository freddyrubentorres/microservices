package com.ms.account_transaction.mapper.impl;

import com.ms.account_transaction.api.controller.dto.AccountDto;
import com.ms.account_transaction.api.controller.dto.TransactionDto;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.domain.model.enums.AccountType;
import com.ms.account_transaction.domain.model.enums.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Freddy Torres
 * file :  AccountMapperImplTest
 * @since : 13/3/2025, jue
 **/

class AccountMapperImplTest {
    private AccountMapperImpl accountMapper;
    @BeforeEach
    public void setUp() {
        accountMapper = new AccountMapperImpl();
    }
    @Test
    public void testToDto_ValidAccount() {
        // Given
        Account account = new Account();
        account.setAccountId(1L);
        account.setAccountNumber(123456L);
        account.setAccountType(AccountType.AHORRO);
        account.setInitialBalance(1000.0);
        account.setStatus(Status.FALSE);
        account.setClientId(10);
        // When
        AccountDto accountDto = accountMapper.toDto(account);
        // Then
        assertNotNull(accountDto);
        assertEquals(account.getAccountId(), accountDto.getAccountId());
        assertEquals(account.getAccountNumber(), accountDto.getAccountNumber());
        assertEquals(account.getAccountType(), accountDto.getAccountType());
        assertEquals(account.getInitialBalance(), accountDto.getInitialBalance());
        assertEquals(account.getStatus(), accountDto.getStatus());
        assertEquals(account.getClientId(), accountDto.getClientId());
    }
    @Test
    public void testToDto_ValidAccountNull() {
        // When
        AccountDto accountDto = accountMapper.toDto(null);
        // Then
        assertNull(accountDto);
    }
}
