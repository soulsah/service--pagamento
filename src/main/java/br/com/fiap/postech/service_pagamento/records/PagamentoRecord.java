package br.com.fiap.postech.service_pagamento.records;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Valid
public record PagamentoRecord(
        Long id,
        String pedidoId,
        double valorTotal,
        String cpf) {
}
