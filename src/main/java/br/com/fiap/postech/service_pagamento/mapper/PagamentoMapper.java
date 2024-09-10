package br.com.fiap.postech.service_pagamento.mapper;

import br.com.fiap.postech.service_pagamento.entity.Pagamento;
import br.com.fiap.postech.service_pagamento.records.PagamentoRecord;
import br.com.fiap.postech.service_pagamento.records.PedidoRecord;

public class PagamentoMapper {

    public static PagamentoRecord mapToRecord(Pagamento pagamento) {
        return new PagamentoRecord(
                pagamento.getId(),
                pagamento.getIdPedido(),
                pagamento.getValorTotal(),
                pagamento.getCpf()
        );
    }

    public static Pagamento mapFromRecord(PedidoRecord pedidoRecord, String qrData) {
        return new Pagamento(
                0L,
                pedidoRecord.pedidoId(),
                pedidoRecord.total(),
                pedidoRecord.clientId().cpf(),
                "PENDENTE",
                qrData
        );
    }
}
