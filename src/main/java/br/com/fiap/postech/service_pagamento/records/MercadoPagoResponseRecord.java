package br.com.fiap.postech.service_pagamento.records;

public record MercadoPagoResponseRecord(
        String in_store_order_id,
        String qr_data
) { }
