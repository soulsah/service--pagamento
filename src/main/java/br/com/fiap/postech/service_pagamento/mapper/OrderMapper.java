package br.com.fiap.postech.service_pagamento.mapper;

import br.com.fiap.postech.service_pagamento.records.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    @Value("${description}")
    private String description;

    @Value("${notification.url}")
    private String notificationUrl;

    @Value("${sponsor}")
    private String sponsor;

    @Value("${title}")
    private String title;

    public OrderRecord mapFromRecord(PedidoRecord pedidoRecord) {
        return new OrderRecord(
                new CashOutRecord(0),
                this.description,
                pedidoRecord.pedidoId(),
                mapItemsFromProdutoRecord(pedidoRecord.produtosIds()),
                this.notificationUrl + pedidoRecord.pedidoId(),
                new SponsorRecord(this.sponsor),
                this.title,
                pedidoRecord.total()
        );
    }

    private List<ItemRecord> mapItemsFromProdutoRecord(List<ProdutoRecord> produtoRecordList) {
        List<ItemRecord> itemRecords = new ArrayList<>();
        for (ProdutoRecord item : produtoRecordList) {
            new ItemRecord(
                item.nome(),
                item.preco(),
                item.quantidadeEstoque(),
                "unit",
                item.preco() * item.quantidadeEstoque()
            );
        }
        return itemRecords;
    }

}
