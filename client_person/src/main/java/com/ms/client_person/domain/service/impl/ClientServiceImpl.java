package com.ms.client_person.domain.service.impl;

import com.ms.client_person.api.controller.dto.ClientDto;
import com.ms.client_person.common.AES256EncryptionService;
import com.ms.client_person.domain.model.entity.Client;
import com.ms.client_person.domain.model.repository.ClientRepository;
import com.ms.client_person.domain.service.ClientService;
import com.ms.client_person.events.Accion;
import com.ms.client_person.events.ClienteEvent;
import com.ms.client_person.exception.ClientNotFoundException;
import com.ms.client_person.mapper.ClientMapper;
import com.ms.client_person.publisher.ClientEventProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Freddy Torres
 * file :  ClientServiceImpl
 * @since : 9/3/2025, dom
 **/

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource("classpath:messages.properties")
public class ClientServiceImpl implements ClientService {

    @Value("${message.entity.no.found}")
    public String ENTITY_NO_FOUND;

    private final ClientEventProducer clientEventProducer;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final AES256EncryptionService encryptionService;

    @Override
    @Transactional(readOnly = true)
    public List<ClientDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new ClientNotFoundException(ENTITY_NO_FOUND);
        }
        return clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClientDto getClientByIdentification(String identification) {
        return clientRepository.findByIdentification(identification)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new ClientNotFoundException(ENTITY_NO_FOUND));
    }

    @Override
    @Transactional
    public ClientDto createClient(ClientDto client) throws Exception {
        client.setClientId(getNextClientId());
        client.setPassword(encryptionService.encrypt(client.getPassword()));
        Client savedClient = clientRepository.save(clientMapper.toEntity(client));
        this.clientEventProducer.sendMessage(new ClienteEvent(Accion.CLIENTE_CREADO,savedClient.getClientId(),savedClient.getName(),savedClient.getLast_name(),savedClient.getIdentification(),client.getAccountType(),client.getInitialBalance()));
        return clientMapper.toDto(savedClient);
    }

    @Override
    @Transactional
    public ClientDto updateClient(Long id, Client client) {
        Client existingClient = clientRepository.findByClientId(id)
                .orElseThrow(() -> new ClientNotFoundException(ENTITY_NO_FOUND));
        if (client.getName() != null) existingClient.setName(client.getName());
        if (client.getPhone() != null) existingClient.setPhone(client.getPhone());
        if (client.getEmail() != null) existingClient.setEmail(client.getEmail());
        if (client.getPassword() != null) existingClient.setPassword(client.getPassword());
        if (client.getAddress() != null) existingClient.setAddress(client.getAddress());
        if (client.getIdentification() != null) existingClient.setIdentification(client.getIdentification());
        if (client.getGender() != null) existingClient.setGender(client.getGender());
        if (client.getLast_name() != null) existingClient.setLast_name(client.getLast_name());
        if (client.getAge() != null) existingClient.setAge(client.getAge());
        if (client.getStatus() != null) existingClient.setStatus(client.getStatus());
        Client savedClient = clientRepository.save(existingClient);
        return clientMapper.toDto(savedClient);
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        if (!clientRepository.existsByClientId(id)) {
            throw new ClientNotFoundException(ENTITY_NO_FOUND);
        }
        clientRepository.deleteByClientId(id);
    }

    public Long getNextClientId() {
        Long maxClientId = clientRepository.findMaxClientId();
        return maxClientId + 1;
    }
}
