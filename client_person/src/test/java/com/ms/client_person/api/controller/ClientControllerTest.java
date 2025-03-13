package com.ms.client_person.api.controller;

import com.ms.client_person.api.controller.dto.ClientDto;
import com.ms.client_person.domain.model.entity.Client;
import com.ms.client_person.domain.service.ClientService;
import com.ms.client_person.dto.response.ApiResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Freddy Torres
 * file :  ClientControllerTest
 * @since : 9/3/2025, dom
 **/

class ClientControllerTest {

    private Client client;
    private final static Long ID = 1L;
    private final static String IDENTIFICACION = "1856893683";

    @Mock
    private AutoCloseable closeable;

    @InjectMocks
    private ClientController clientController;

    @Mock
    private ClientService clientService;

    List<ClientDto> clientDtoList;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        client = new Client();
        clientDtoList = new ArrayList<>();
    }

    @AfterEach
    void closeService() throws Exception {
        closeable.close();
    }

    @Test
    void getPing_OK() {
        // When
        ResponseEntity<String> responseEntity = clientController.ping();
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getAllClients() {
        // Given
        when(clientService.getAllClients()).thenReturn(clientDtoList);
        // When
        ResponseEntity<ApiResponse<List<ClientDto>>> responseEntity = clientController.getAllClients();
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void getClientByIdentification() {
        // When
        ResponseEntity<ApiResponse<ClientDto>> responseEntity = clientController.getClientByIdentification(IDENTIFICACION);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void createClient() throws Exception {
        // Given
        ClientDto client = new ClientDto();
       // When
        ResponseEntity<ApiResponse<ClientDto>> responseEntity = clientController.createClient(client);
        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void updateClient() {
        // When
        ResponseEntity<ApiResponse<ClientDto>> responseEntity = clientController.updateClient(ID, client);
        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    void deleteClient() {
        // When
        doNothing().when(clientService).deleteClient(ID);
        ResponseEntity<ApiResponse<Void>> responseEntity = clientController.deleteClient(ID);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
