package br.com.fiap.postech.service_pagamento.service.impl;

import br.com.fiap.postech.service_pagamento.mapper.OrderMapper;
import br.com.fiap.postech.service_pagamento.records.OrderRecord;
import br.com.fiap.postech.service_pagamento.service.MercadoPagoService;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    @Override
    public String createOrder(OrderRecord orderRecord) {
        Order order = OrderMapper.mapFromRecord(orderRecord);

        return "";
    }

}
