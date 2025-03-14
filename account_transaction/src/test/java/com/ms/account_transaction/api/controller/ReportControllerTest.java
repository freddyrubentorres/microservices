package com.ms.account_transaction.api.controller;

import com.ms.account_transaction.domain.service.ReportService;
import com.ms.account_transaction.dto.response.ApiResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;

import java.text.ParseException;
import java.util.*;

/**
 * @author : Freddy Torres
 * file :  ReportControllerTest
 * @since : 12/3/2025, mié
 **/

class ReportControllerTest {

    private final static String IDENTIFICACION = "1856893683";
    private final static Date STARTDATE = new Date();
    private final static Date ENDDATE = new Date();

    @InjectMocks
    private ReportController reportController;

    @Mock
    private ReportService reportService;

    @Mock
    private AutoCloseable closeable;

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
        ResponseEntity<String> responseEntity = reportController.ping();
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getTransactionsByDateAndPerson() throws ParseException {
        // Given
        List<Map<String, Object>> listaDeMapas = new ArrayList<>();
        Map<String, Object> mapa1 = new HashMap<>();
        mapa1.put("clave", "valor");
        listaDeMapas.add(mapa1);
        // When
        when(reportService.getTransactionsByDateAndPerson(STARTDATE, ENDDATE, IDENTIFICACION)).thenReturn(listaDeMapas);
        ResponseEntity<ApiResponse<List<Map<String, Object>>>> responseEntity = reportController.getTransactionsByDateAndPerson(STARTDATE, ENDDATE, IDENTIFICACION);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
