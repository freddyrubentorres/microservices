package com.ms.account_transaction.consumer.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.account_transaction.constants.ProcessConstants;
import com.ms.account_transaction.consumer.ClientIntegrationRepository;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.domain.model.entity.Transaction;
import com.ms.account_transaction.domain.model.enums.AccountType;
import com.ms.account_transaction.domain.model.enums.Status;
import com.ms.account_transaction.domain.model.enums.TransactionType;
import com.ms.account_transaction.domain.repository.AccountRepository;
import com.ms.account_transaction.domain.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @author : Freddy Torres
 * file :  ClientIntegrationRepositoryImpl
 * @since : 13/3/2025, jue
 **/

@Repository
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class ClientIntegrationRepositoryImpl implements ClientIntegrationRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientIntegrationRepositoryImpl.class);

    @Value("${message.integration.process.event}")
    public String PROCESS_OK;

    Random random = new Random();
    Map<String, Object> jsonMap;
    Account account = new Account();
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional
    @RabbitListener(queues = ProcessConstants.CLIENT_QUEUE)
    public void processEvent(String event) throws JsonProcessingException {
        if (!event.isEmpty()) {
            try {
                LOGGER.info(PROCESS_OK, event);
                jsonMap = objectMapper.readValue(event, Map.class);
                account.setAccountNumber(100000L + random.nextInt(900000));
                account.setAccountType(AccountType.valueOf((String) jsonMap.get("accountType")));
                account.setInitialBalance((Double) jsonMap.get("initialBalance"));
                account.setStatus(Status.TRUE);
                account.setClientId((Integer) jsonMap.get("clienteId"));
                this.accountRepository.save(account);
                if (account.getStatus().equals(Status.TRUE)) {
                    Transaction transaction = new Transaction();
                    transaction.setDescription(ProcessConstants.TRANSACTION_ACCOUNT_OPENING);
                    transaction.setDate(new Date());
                    transaction.setTransactionType(TransactionType.DEPOSITO);
                    transaction.setAmount(account.getInitialBalance());
                    transaction.setBalance(account.getInitialBalance());
                    transaction.setAccount(account);
                    transactionRepository.save(transaction);
                }
                LOGGER.info("Cuenta y movimiento procesada correctamente.");
            } catch (Exception e) {
                LOGGER.error("Se presento un error: {}", e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
