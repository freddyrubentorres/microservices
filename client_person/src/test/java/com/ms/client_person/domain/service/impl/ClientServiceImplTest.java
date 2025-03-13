package com.ms.client_person.domain.service.impl;

import com.ms.client_person.api.controller.dto.ClientDto;
import com.ms.client_person.common.AES256EncryptionService;
import com.ms.client_person.domain.model.entity.Client;
import com.ms.client_person.domain.model.enums.Gender;
import com.ms.client_person.domain.model.enums.Status;
import com.ms.client_person.domain.model.repository.ClientRepository;
import com.ms.client_person.exception.ClientNotFoundException;
import com.ms.client_person.mapper.ClientMapper;
import com.ms.client_person.publisher.ClientEventProducer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * @author : Freddy Torres
 * file :  ClientServiceImplTest
 * @since : 10/3/2025, lun
 **/

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper clientMapper;
    @InjectMocks
    private ClientServiceImpl clientService;
    private Client client;
    private ClientDto clientDto;
    private final static String IDENTIFICACION = "ID12345";
    @Mock
    private AES256EncryptionService encryptionService;
    @Mock
    private ClientEventProducer clientEventProducer;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setClientId(1L);
        client.setName("John Doe");
        client.setIdentification(IDENTIFICACION);
        client.setPassword("old_password");
        client.setStatus(Status.TRUE);
        client.setGender(Gender.M);
        client.setAge(35L);
        client.setAddress("old address");
        client.setPhone("555-1234");
        clientDto = new ClientDto();
        clientDto.setClientId(1L);
        clientDto.setName("John Doe");
        clientDto.setIdentification(IDENTIFICACION);
    }

    @Test
    void getAllClientsIsNull() {
        // When
        when(clientRepository.findAll()).thenReturn(List.of());
        // Then
        assertThrows(ClientNotFoundException.class, () -> clientService.getAllClients());
    }

    @Test
    void getAllClientsNotNull() {
        // Given
        List<Client> clients = Arrays.asList(client, new Client());
        // When
        when(clientRepository.findAll()).thenReturn(clients);
        when(clientMapper.toDto(any(Client.class))).thenReturn(clientDto, new ClientDto());
        List<ClientDto> result = clientService.getAllClients();
        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    void getClientByIdentification() {
        // When
        when(clientRepository.findByIdentification(IDENTIFICACION)).thenReturn(Optional.empty());
        // Then
        assertThrows(ClientNotFoundException.class, () -> clientService.getClientByIdentification(IDENTIFICACION));
    }

    @Test
    void createClient() throws Exception {
        /*// When
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(clientDto);
        when(encryptionService.encrypt(client.getPassword())).thenReturn("encryptedPassword");
        ClientDto result = clientService.createClient(client);
        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getName());*/
    }

    @Test
    void updateClientNoFound() {
        // Given
        Client updatedClient = new Client();
        // When
        when(clientRepository.findByClientId(1L)).thenReturn(Optional.empty());
        // Then
        assertThrows(ClientNotFoundException.class, () -> clientService.updateClient(1L, updatedClient));
    }

    @Test
    void updateClient() {
        // Given


        // When
        when(clientRepository.findByClientId(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(any(Client.class))).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(clientDto);
        ClientDto result = clientService.updateClient(1L, client);
        // Then
        assertNotNull(result);
    }

    @Test
    void deleteClient() {
        // When
        when(clientRepository.existsByClientId(1L)).thenReturn(true);
        clientService.deleteClient(1L);
        // Then
        verify(clientRepository, times(1)).deleteByClientId(1L);
    }

    @Test
    void deleteClientNoFound() {
        // When
        when(clientRepository.existsByClientId(1L)).thenReturn(false);
        // Then
        assertThrows(ClientNotFoundException.class, () -> clientService.deleteClient(1L));
    }
}
