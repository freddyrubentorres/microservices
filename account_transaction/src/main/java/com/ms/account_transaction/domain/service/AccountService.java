package com.ms.account_transaction.domain.service;

import com.ms.account_transaction.api.controller.dto.AccountDto;
import com.ms.account_transaction.domain.model.entity.Account;

import java.util.List;

/**
 * @author : Freddy Torres
 * file :  AccountService
 * @since : 13/3/2025, jue
 **/

public interface AccountService {
    List<AccountDto> getAllAccount();
    List<AccountDto> getAccountById(Long id);
    Account createAccount(Account account);
    void deleteAccount(Long id);
}
