package com.ms.account_transaction.mapper.impl;

import com.ms.account_transaction.api.controller.dto.AccountDto;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.mapper.AccountMapper;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file :  AccountMapperImpl
 * @since : 13/3/2025, jue
 **/

@Component
public class AccountMapperImpl implements AccountMapper {
    @Override
    public AccountDto toDto(Account account) {
        if (account == null) {
            return null;
        }
        AccountDto accountDto = new AccountDto();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setAccountNumber(account.getAccountNumber());
        accountDto.setAccountType(account.getAccountType());
        accountDto.setInitialBalance(account.getInitialBalance());
        accountDto.setStatus(account.getStatus());
        accountDto.setClientId(account.getClientId());
        return accountDto;
    }
}
