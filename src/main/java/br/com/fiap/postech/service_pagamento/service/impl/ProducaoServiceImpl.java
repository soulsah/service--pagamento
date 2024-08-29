package br.com.fiap.postech.service_pagamento.service.impl;

import br.com.fiap.postech.service_pagamento.repository.PagamentoQueueProducer;
import br.com.fiap.postech.service_pagamento.service.ProducaoService;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProducaoServiceImpl implements ProducaoService {

    @Autowired
    private PagamentoQueueProducer pagamentoQueueProducer;

    @Override
    public void notificarPagamento(String pedidoId) {

        pagamentoQueueProducer.enviarMensagem(new Queue("pagamento-response-sucesso-queue"), pedidoId);
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.postForEntity(
//            "http://localhost:8085/producao/pedidos/receberPedido/" + pedidoId,
//            null,
//            String.class);
    }
}
