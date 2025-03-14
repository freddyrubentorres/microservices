package com.ms.account_transaction.domain.service.impl;

import com.ms.account_transaction.api.controller.dto.TransactionDto;
import com.ms.account_transaction.constants.ProcessConstants;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.domain.model.entity.Transaction;
import com.ms.account_transaction.domain.model.enums.TransactionType;
import com.ms.account_transaction.domain.repository.TransactionRepository;
import com.ms.account_transaction.domain.service.TransactionService;
import com.ms.account_transaction.exception.GeneralException;
import com.ms.account_transaction.mapper.TransactionMapper;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import com.ms.account_transaction.domain.repository.AccountTransactionsRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Freddy Torres
 * file :  TransactionServiceImpl
 * @since : 11/3/2025, mar
 **/

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class TransactionServiceImpl implements TransactionService {

    @Value("${message.entity.no.found}")
    public String ENTITY_NO_FOUND;

    Transaction transactionEntity;

    @Autowired
    private final TransactionMapper transactionMapper;

    private final TransactionRepository transactionRepository;

    private final AccountTransactionsRepository accountTransactionsRepository;

    @Override
    @Transactional
    public TransactionDto createTransaction(Transaction transaction) {
        Optional<Transaction> transactionProcess = transactionRepository.findTopByAccountOrderByDateDesc(transaction.getAccount());
        if (transactionProcess.isPresent()) {
            if (transaction.getAmount() > transactionProcess.get().getBalance() && transaction.getAmount()<0) {
                throw new GeneralException(ProcessConstants.MSG_INSUFFICIENT_BALANCE);
            }
            transactionEntity = new Transaction();
            transactionEntity.setDescription(transaction.getDescription());
            transactionEntity.setDate(new Date());
            transactionEntity.setTransactionType(transaction.getAmount() > 0 ? TransactionType.DEPOSITO : TransactionType.DEBITO);
            transactionEntity.setAmount(transaction.getAmount());
            transactionEntity.setBalance(transaction.getAmount() > 0 ? transaction.getAmount()+transactionProcess.get().getBalance() : transactionProcess.get().getBalance()+transaction.getAmount());
            transactionEntity.setAccount(transaction.getAccount());
            return transactionMapper.toDto(transactionRepository.save(transactionEntity));
        }
        throw new GeneralException(ENTITY_NO_FOUND);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionDto> getTransactionsByAccount(Long accountId) {
        Account accountEntity= new Account();
        accountEntity.setAccountId(accountId);
        List<Transaction> accountsList = transactionRepository.findByAccountOrderByDateDesc(accountEntity);
        if (accountsList.isEmpty()) {
            throw new GeneralException(ENTITY_NO_FOUND);
        }
        return accountsList.stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionDto> getTransactionsByClientId(Long clientId) {
        List<Transaction> accountsList = transactionRepository.findByAccount_ClientIdOrderByDateDesc(Math.toIntExact(clientId));
        if (accountsList.isEmpty()) {
            throw new GeneralException(ENTITY_NO_FOUND);
        }
        return accountsList.stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }
}
