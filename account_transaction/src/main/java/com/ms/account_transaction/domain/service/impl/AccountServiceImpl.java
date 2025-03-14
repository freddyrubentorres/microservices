package com.ms.account_transaction.domain.service.impl;

import com.ms.account_transaction.api.controller.dto.AccountDto;
import com.ms.account_transaction.constants.ProcessConstants;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.domain.model.entity.Transaction;
import com.ms.account_transaction.domain.model.enums.Status;
import com.ms.account_transaction.domain.model.enums.TransactionType;
import com.ms.account_transaction.domain.repository.AccountRepository;
import com.ms.account_transaction.domain.repository.TransactionRepository;
import com.ms.account_transaction.domain.service.AccountService;

import com.ms.account_transaction.exception.GeneralException;
import com.ms.account_transaction.mapper.AccountMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Random;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Freddy Torres
 * file :  AccountServiceImpl
 * @since : 13/3/2025, jue
 **/

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class AccountServiceImpl implements AccountService {

    @Value("${message.entity.no.found}")
    public String ENTITY_NO_FOUND;

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    private final AccountMapper accountMapper;

    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        if (accounts.isEmpty()) {
            throw new GeneralException(ENTITY_NO_FOUND);
        }
        return accounts.stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional(readOnly = true)
    public List<AccountDto> getAccountById(Long id) {
        List<Account> accounts = accountRepository.findByAccountId(id);
        if (accounts.isEmpty()) {
            throw new GeneralException(ENTITY_NO_FOUND);
        }
        return accounts.stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public Account createAccount(Account account) {
        Random random = new Random();
        account.setAccountNumber(100000L + random.nextInt(900000));
        Account accountCreate= accountRepository.save(account);
        if(accountCreate.getStatus().equals(Status.TRUE)){
            Transaction transaction= new Transaction();
            transaction.setDescription(ProcessConstants.TRANSACTION_ACCOUNT_OPENING);
            transaction.setDate(new Date());
            transaction.setTransactionType(TransactionType.DEPOSITO);
            transaction.setAmount(account.getInitialBalance());
            transaction.setBalance(account.getInitialBalance());
            transaction.setAccount(accountCreate);
            transactionRepository.save(transaction);
        }
        return accountCreate;
    }
    @Override
    @Transactional
    public void deleteAccount(Long id) {
        if (!accountRepository.existsByAccountId(id)) {
            throw new GeneralException(ENTITY_NO_FOUND);
        }
        accountRepository.deleteByAccountId(id);
    }
}
