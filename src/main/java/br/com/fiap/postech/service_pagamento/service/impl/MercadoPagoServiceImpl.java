package br.com.fiap.postech.service_pagamento.service.impl;

import br.com.fiap.postech.service_pagamento.mapper.OrderMapper;
import br.com.fiap.postech.service_pagamento.records.MercadoPagoResponseRecord;
import br.com.fiap.postech.service_pagamento.records.OrderRecord;
import br.com.fiap.postech.service_pagamento.records.PedidoRecord;
import br.com.fiap.postech.service_pagamento.service.MercadoPagoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    @Value("${access.token}")
    private String accessToken;

    @Override
    public MercadoPagoResponseRecord createOrder(PedidoRecord pedidoRecord) throws RestClientException {
        OrderRecord orderRecord = new OrderMapper().mapFromRecord(pedidoRecord);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer "+accessToken);
        HttpEntity<String> entity = new HttpEntity<>(convertOrderToJson(orderRecord), headers);
        RestTemplate restTemplate = new RestTemplate();

        var response = restTemplate.postForObject(
            "https://api.mercadopago.com/instore/orders/qr/seller/collectors/144273275/pos/POS01/qrs",
                entity,
                MercadoPagoResponseRecord.class);
        return response;
    }

    private String convertOrderToJson(OrderRecord orderRecord) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(orderRecord);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting OrderRecord to JSON", e);
        }
    }

}
