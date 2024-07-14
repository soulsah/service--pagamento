package br.com.fiap.postech.service_pagamento.records;

public record ClienteRecord(
        long id,
        String cpf,
        String email,
        String nome
) {
}
