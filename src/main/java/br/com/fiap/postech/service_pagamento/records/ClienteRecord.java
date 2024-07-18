package br.com.fiap.postech.service_pagamento.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ClienteRecord(
        long id,
        @JsonProperty("CPF") String cpf,
        String email,
        String nome
) {
}
