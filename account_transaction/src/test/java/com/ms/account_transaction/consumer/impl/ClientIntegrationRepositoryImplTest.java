package com.ms.account_transaction.consumer.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.account_transaction.api.controller.dto.AccountDto;
import com.ms.account_transaction.constants.ProcessConstants;
import com.ms.account_transaction.domain.model.entity.Account;
import com.ms.account_transaction.domain.model.entity.Transaction;
import com.ms.account_transaction.domain.model.enums.AccountType;
import com.ms.account_transaction.domain.model.enums.Status;
import com.ms.account_transaction.domain.model.enums.TransactionType;
import com.ms.account_transaction.domain.repository.AccountRepository;
import com.ms.account_transaction.domain.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * @author : Freddy Torres
 * file :  ClientIntegrationRepositoryImplTest
 * @since : 13/3/2025, jue
 **/

class ClientIntegrationRepositoryImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientIntegrationRepositoryImplTest.class);

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private ClientIntegrationRepositoryImpl clientIntegrationRepository;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testProcess() throws JsonProcessingException {
        String event = "{ \"accountType\": \"AHORRO\", \"initialBalance\": 1000.0, \"clienteId\": 12345 }";
        clientIntegrationRepository.processEvent(event);
        verify(accountRepository).save(any(Account.class));
        verify(transactionRepository).save(any(Transaction.class));
    }
    @Test
    public void testProcess_Error() throws JsonProcessingException {
        String event = "{ \"accountType\": \"AHORRO,\", \"initialBalance\": 1000.0, \"clienteId\": 12345 }";
        try {
            clientIntegrationRepository.processEvent(event);
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }

}
