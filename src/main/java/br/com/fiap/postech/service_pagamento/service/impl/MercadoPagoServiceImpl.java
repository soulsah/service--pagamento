package br.com.fiap.postech.service_pagamento.service.impl;

import br.com.fiap.postech.service_pagamento.mapper.OrderMapper;
import br.com.fiap.postech.service_pagamento.records.MercadoPagoResponseRecord;
import br.com.fiap.postech.service_pagamento.records.OrderRecord;
import br.com.fiap.postech.service_pagamento.records.PedidoRecord;
import br.com.fiap.postech.service_pagamento.service.MercadoPagoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    @Override
    public MercadoPagoResponseRecord createOrder(PedidoRecord pedidoRecord) throws RestClientException {
        OrderRecord orderRecord = new OrderMapper().mapFromRecord(pedidoRecord);
        var accessToken = "TEST-4026587014474346-030316-7a2555645ba9ed9c9cddecfa08199184-144273275";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<String> entity = new HttpEntity<>(convertOrderToJson(orderRecord), headers);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.postForObject(
            "https://api.mercadopago.com/instore/orders/qr/seller/collectors/144273275/pos/POS01/qrs",
                entity,
                MercadoPagoResponseRecord.class);
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
