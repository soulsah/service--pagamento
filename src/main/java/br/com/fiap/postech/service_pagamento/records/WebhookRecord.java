package br.com.fiap.postech.service_pagamento.records;

public record WebhookRecord(
        String resource,
        String topic) {
}
