package br.com.fiap.postech.service_pagamento.records;

import java.util.List;

public record PedidoRecord(
    String pedidoId,
    ClienteRecord clienteId,
    List<ProdutoRecord> produtosIds,
    double total,
    String status
) { }
