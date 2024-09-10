package br.com.fiap.postech.service_pagamento.records;

import java.io.Serializable;
import java.util.List;

public record PedidoRecord(
    String pedidoId,
    ClienteRecord clientId,
    List<ProdutoRecord> produtosIds,
    double total,
    String status
) implements Serializable { }
