package br.com.fiap.postech.service_pagamento.records;

public record ItemRecord(
        String title,
        double unit_price,
        int quantity,
        String unit_measure,
        double total_amount
) {}
