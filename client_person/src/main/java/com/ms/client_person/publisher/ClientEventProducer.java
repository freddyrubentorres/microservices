package com.ms.client_person.publisher;

import com.ms.client_person.events.ClienteEvent;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * @author : Freddy Torres
 * file :  ClientEventProducer
 * @since : 9/3/2025, dom
 **/

@Service
@RequiredArgsConstructor
public class ClientEventProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientEventProducer.class);
    @Value("${rabbitmq.queue.routing.key}")
    private String routingKey;
    @Value("${rabbitmq.queue.exchange}")
    private String exchange;
    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(ClienteEvent message) {
        LOGGER.info(String.format("message sent : %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
