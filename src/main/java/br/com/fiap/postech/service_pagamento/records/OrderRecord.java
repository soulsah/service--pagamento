package br.com.fiap.postech.service_pagamento.records;

import java.util.List;

public record OrderRecord(
        CashOutRecord cash_out,
        String description,
        String external_reference,
        List<ItemRecord> items,
        String notification_url,
        SponsorRecord sponsor,
        String title,
        double total_amount
) {}
