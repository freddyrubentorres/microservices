package com.ms.account_transaction.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author : Freddy Torres
 * file :  ClientIntegrationRepository
 * @since : 13/3/2025, jue
 **/

public interface ClientIntegrationRepository {
    void processEvent(String event) throws JsonProcessingException;
}
