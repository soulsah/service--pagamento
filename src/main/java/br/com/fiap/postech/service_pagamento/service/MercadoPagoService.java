package br.com.fiap.postech.service_pagamento.service;

import br.com.fiap.postech.service_pagamento.records.OrderRecord;

public interface MercadoPagoService {

    String createOrder(OrderRecord orderRecord);
}
