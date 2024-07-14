package br.com.fiap.postech.service_pagamento.service;

import br.com.fiap.postech.service_pagamento.records.PagamentoRecord;

public interface PagamentoService {

    void createPagamento(PagamentoRecord pagamentoRecord);
    void updateStatusPagamento(String idPagamento, String statusPagamento);
    PagamentoRecord findPagamentoById(String idPagamento);

}
