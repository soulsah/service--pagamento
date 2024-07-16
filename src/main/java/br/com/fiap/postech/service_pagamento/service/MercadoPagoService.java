package br.com.fiap.postech.service_pagamento.service;

import br.com.fiap.postech.service_pagamento.records.MercadoPagoResponseRecord;
import br.com.fiap.postech.service_pagamento.records.OrderRecord;
import br.com.fiap.postech.service_pagamento.records.PedidoRecord;

public interface MercadoPagoService {

    MercadoPagoResponseRecord createOrder(PedidoRecord pedidoRecord);
}
