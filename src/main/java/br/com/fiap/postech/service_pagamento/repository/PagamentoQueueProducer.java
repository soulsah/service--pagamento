package br.com.fiap.postech.service_pagamento.repository;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PagamentoQueueProducer {

    @Autowired
    private RabbitTemplate template;

    public void enviarMensagem(Queue queue, String message) {
        this.template.convertAndSend(queue.getName(), message);
        System.out.println("Mensagem enviada com sucesso para o pedido: " + message);
    }

}
