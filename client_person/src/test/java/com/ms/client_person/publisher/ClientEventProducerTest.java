package com.ms.client_person.publisher;

import com.ms.client_person.api.controller.enums.AccountType;
import com.ms.client_person.events.Accion;
import com.ms.client_person.events.ClienteEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

/**
 * @author : Freddy Torres
 * file :  ClientEventProducerTest
 * @since : 10/3/2025, lun
 **/

class ClientEventProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;
    @InjectMocks
    private ClientEventProducer clientEventProducer;
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
    void sendMessage_shouldSendMessageToRabbitTemplate() {
        // Given
        ClienteEvent clienteEvent = new ClienteEvent(
                Accion.CLIENTE_CREADO,
                1L,
                "JUAN",
                "PEREZ",
                "1845394285",
                AccountType.AHORRO,
                200.00
        );
        // When
        clientEventProducer.sendMessage(clienteEvent);
        // Then
        verify(rabbitTemplate, times(1));
    }
}