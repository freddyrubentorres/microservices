package com.ms.client_person.api.controller;

import com.ms.client_person.api.controller.dto.ClientDto;
import com.ms.client_person.common.ResponseUtil;
import com.ms.client_person.constants.ProcessConstants;
import com.ms.client_person.domain.model.entity.Client;
import com.ms.client_person.domain.service.ClientService;
import com.ms.client_person.dto.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

/**
 * @author : Freddy Torres
 * file :  ClientController
 * @since : 9/3/2025, dom
 **/

@PropertySource("classpath:messages.properties")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessConstants.API_PATH_CLIENTS)
public class ClientController {

    @Value("${controller.ok}")
    public String CONTROLLER_OK;

    private final ClientService clientService;

    @GetMapping(ProcessConstants.API_PATH_CLIENTS_PING)
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("{\"status\":\"OK\"}");
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ClientDto>>> getAllClients() {
        List<ClientDto> clients = clientService.getAllClients();
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, clients));
    }

    @GetMapping(ProcessConstants.API_PATH_CLIENTS_BY_IDENTIFICACION)
    public ResponseEntity<ApiResponse<ClientDto>> getClientByIdentification(@PathVariable String identification) {
        ClientDto client = clientService.getClientByIdentification(identification);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, client));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ClientDto>> createClient(@Valid @RequestBody ClientDto client) throws Exception {
        ClientDto createdClient = clientService.createClient(client);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(CONTROLLER_OK, createdClient), HttpStatus.CREATED);
    }

    @PatchMapping(ProcessConstants.API_PATH_CLIENTS_PATCH_ID)
    public ResponseEntity<ApiResponse<ClientDto>> updateClient(@PathVariable Long id, @RequestBody Client client) {
        ClientDto updatedClient = clientService.updateClient(id, client);
        return new ResponseEntity<>(ResponseUtil.createSuccessResponse(CONTROLLER_OK, updatedClient), HttpStatus.CREATED);
    }

    @DeleteMapping(ProcessConstants.API_PATH_CLIENTS_DELETE_ID)
    public ResponseEntity<ApiResponse<Void>> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, null));
    }
}
