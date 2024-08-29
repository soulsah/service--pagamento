package br.com.fiap.postech.service_pagamento.repository;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class PedidoQueueReceiver {

    @Autowired
    private RabbitTemplate template;

    @RabbitListener(queues = {"pedido-request-sucesso-queue"})
    public void receive(@Payload byte[] message) {
        System.out.println("Pedido recebido");
        String payload = new String(message, StandardCharsets.UTF_8);
        System.out.println("Payload: " + payload);
    }

}
