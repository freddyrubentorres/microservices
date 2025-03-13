package com.ms.client_person.mapper.impl;

import com.ms.client_person.api.controller.dto.ClientDto;
import com.ms.client_person.domain.model.entity.Client;
import com.ms.client_person.mapper.ClientMapper;
import org.springframework.stereotype.Component;

/**
 * @author : Freddy Torres
 * file :  ClientMapperImpl
 * @since : 9/3/2025, dom
 **/
@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public ClientDto toDto(Client client) {
        if (client == null) {
            return null;
        }
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(client.getClientId());
        clientDto.setEmail(client.getEmail());
        clientDto.setPassword(client.getPassword());
        clientDto.setStatus(client.getStatus());
        clientDto.setPersonId(client.getPersonId());
        clientDto.setName(client.getName());
        clientDto.setLast_name(client.getLast_name());
        clientDto.setGender(client.getGender());
        clientDto.setAge(client.getAge());
        clientDto.setIdentification(client.getIdentification());
        clientDto.setAddress(client.getAddress());
        clientDto.setPhone(client.getPhone());
        clientDto.setAccountType(client.getAccountType());
        clientDto.setInitialBalance(client.getInitialBalance());
        return clientDto;
    }

    @Override
    public Client toEntity(ClientDto clientDto) {
        if (clientDto == null) {
            return null;
        }
        Client client= new Client();
        client.setClientId(clientDto.getClientId());
        client.setEmail(clientDto.getEmail());
        client.setPassword(clientDto.getPassword());
        client.setStatus(clientDto.getStatus());
        client.setPersonId(clientDto.getPersonId());
        client.setName(clientDto.getName());
        client.setLast_name(clientDto.getLast_name());
        client.setGender(clientDto.getGender());
        client.setAge(clientDto.getAge());
        client.setIdentification(clientDto.getIdentification());
        client.setAddress(clientDto.getAddress());
        client.setPhone(clientDto.getPhone());
        client.setAccountType(clientDto.getAccountType());
        client.setInitialBalance(clientDto.getInitialBalance());
        return client;
    }
}
