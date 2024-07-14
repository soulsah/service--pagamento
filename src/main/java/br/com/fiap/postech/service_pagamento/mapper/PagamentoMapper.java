package br.com.fiap.postech.service_pagamento.mapper;

import br.com.fiap.postech.service_pagamento.entity.Pagamento;
import br.com.fiap.postech.service_pagamento.records.PagamentoRecord;

public class PagamentoMapper {

    public static PagamentoRecord mapToRecord(Pagamento pagamento) {
        return new PagamentoRecord(
                pagamento.getId(),
                pagamento.getIdPedido(),
                pagamento.getValorTotal(),
                pagamento.getCpf()
        );
    }

    public static Pagamento mapFromRecord(PagamentoRecord pagamentoRecord) {
        return new Pagamento(
                pagamentoRecord.id(),
                pagamentoRecord.pedidoId(),
                pagamentoRecord.valorTotal(),
                pagamentoRecord.cpf()
        );
    }
}
