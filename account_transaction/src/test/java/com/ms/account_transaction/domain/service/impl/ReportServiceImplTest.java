package com.ms.account_transaction.domain.service.impl;

import com.ms.account_transaction.domain.repository.AccountTransactionsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Value;

import java.text.ParseException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author : Freddy Torres
 * file :  ReportServiceImplTest
 * @since : 13/3/2025, jue
 **/

class ReportServiceImplTest {
    @Mock
    private AccountTransactionsRepository accountTransactionsRepository;

    @InjectMocks
    private ReportServiceImpl reportServiceImpl;

    @Value("${message.entity.no.found}")
    private String ENTITY_NO_FOUND;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        ENTITY_NO_FOUND = "Entity not found";
    }

    @Test
    public void testGetTransactionsByDateAndPerson_TransactionsFound() throws ParseException {
        // Given
        Date startDate = new Date();
        Date endDate = new Date();
        String identification = "123456789";
        List<Map<String, Object>> mockTransactions = new ArrayList<>();
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("id", 1);
        transaction.put("amount", 100.0);
        mockTransactions.add(transaction);
        // When
        when(accountTransactionsRepository.procedureName(identification, startDate, endDate)).thenReturn(mockTransactions);
        List<Map<String, Object>> result = reportServiceImpl.getTransactionsByDateAndPerson(startDate, endDate, identification);
        // Then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(100.0, result.get(0).get("amount"));
    }
}
