package com.ms.client_person.domain.service;


import com.ms.client_person.api.controller.dto.ClientDto;
import com.ms.client_person.domain.model.entity.Client;

import java.util.List;

/**
 * @author : Freddy Torres
 * file :  ClientService
 * @since : 9/3/2025, dom
 **/

public interface  ClientService {
    List<ClientDto> getAllClients();
    ClientDto getClientByIdentification(String identification);
    ClientDto createClient(ClientDto clientDto) throws Exception;
    ClientDto updateClient(Long id, Client client);
    void deleteClient(Long id);
}
