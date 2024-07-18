package br.com.fiap.postech.service_pagamento.records;

public record ProdutoRecord(
        Long id,
        String nome,
        String descricao,
        double preco,
        Integer quantidade,
        String status
) {
}
