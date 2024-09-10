package br.com.fiap.postech.service_pagamento.mapper;

import br.com.fiap.postech.service_pagamento.records.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    @Value("${description.texto}")
    private String descriptionTexto;

    @Value("${notification.url}")
    private String notificationUrl;

    @Value("${sponsor}")
    private String sponsor;

    @Value("${title.texto}")
    private String titleTexto;

    public OrderRecord mapFromRecord(PedidoRecord pedidoRecord) {
        return new OrderRecord(
                new CashOutRecord(0),
                "descricao",
                pedidoRecord.pedidoId(),
                mapItemsFromProdutoRecord(pedidoRecord.produtosIds()),
                "https://webhook-test.com/df2ebef92e0eddaae7cddf22d9a5f7c5",
//                this.notificationUrl + pedidoRecord.pedidoId(),
                new SponsorRecord(189254301),
                "titulo",
                pedidoRecord.total()
        );
    }

    private List<ItemRecord> mapItemsFromProdutoRecord(List<ProdutoRecord> produtoRecordList) {
        List<ItemRecord> itemRecords = new ArrayList<>();
        for (ProdutoRecord item : produtoRecordList) {
            itemRecords.add(new ItemRecord(
                item.nome(),
                item.preco(),
                item.quantidade(),
                "unit",
                item.preco() * item.quantidade()
            ));
        }
        return itemRecords;
    }

}
