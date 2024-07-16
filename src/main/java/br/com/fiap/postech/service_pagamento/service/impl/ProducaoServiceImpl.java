package br.com.fiap.postech.service_pagamento.service.impl;

import br.com.fiap.postech.service_pagamento.service.ProducaoService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProducaoServiceImpl implements ProducaoService {

    @Override
    public void notificarPagamento(String pedidoId) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity(
            "http://localhost:8085/producao/pedidos/receberPedido/" + pedidoId,
            null,
            String.class);
    }
}
