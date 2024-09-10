package br.com.fiap.postech.service_pagamento.repository;

import br.com.fiap.postech.service_pagamento.records.PedidoRecord;
import br.com.fiap.postech.service_pagamento.service.MercadoPagoService;
import br.com.fiap.postech.service_pagamento.service.PagamentoService;
import com.google.gson.Gson;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class PedidoQueueReceiver {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @RabbitListener(queues = {"pedido-request-sucesso-queue"})
    public void receive(@Payload byte[] message) {
        System.out.println("Pedido recebido");
        String payload = new String(message, StandardCharsets.UTF_8);
        var pedidoRecord = new Gson().fromJson(payload, PedidoRecord.class);
        var response = mercadoPagoService.createOrder(pedidoRecord);
        pagamentoService.createPagamento(pedidoRecord, response.qr_data());
        System.out.println(response);
    }

}
