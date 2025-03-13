package com.ms.client_person.mapper;

import com.ms.client_person.api.controller.dto.ClientDto;
import com.ms.client_person.domain.model.entity.Client;
import org.mapstruct.Mapper;

/**
 * @author : Freddy Torres
 * file :  ClientMapper
 * @since : 9/3/2025, dom
 **/

@Mapper(componentModel = "spring")
public interface  ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);

}
