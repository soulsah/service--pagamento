package br.com.fiap.postech.service_pagamento.service;

import br.com.fiap.postech.service_pagamento.records.PagamentoRecord;
import br.com.fiap.postech.service_pagamento.records.PedidoRecord;

public interface PagamentoService {
    void createPagamento(PedidoRecord pedidoRecord);
    void updateStatusPagamento(String idPedido, String status);
    PagamentoRecord findPagamentoById(String idPagamento);
}
