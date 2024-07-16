package br.com.fiap.postech.service_pagamento.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.fiap.postech.service_pagamento.entity.Pagamento;
import br.com.fiap.postech.service_pagamento.records.PagamentoRecord;
import br.com.fiap.postech.service_pagamento.records.PedidoRecord;
import br.com.fiap.postech.service_pagamento.records.ClienteRecord;

import br.com.fiap.postech.service_pagamento.records.ProdutoRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PagamentoMapperTest {

    @Test
    public void testMapToRecord() {
        // Arrange
        Pagamento pagamento = new Pagamento(1L, "12313413-4134134134", 100.0, "12345678901", "PENDENTE");

        // Act
        PagamentoRecord pagamentoRecord = PagamentoMapper.mapToRecord(pagamento);

        // Assert
        assertEquals(1L, pagamentoRecord.id());
        assertEquals("12313413-4134134134", pagamentoRecord.pedidoId());
        assertEquals(100.0, pagamentoRecord.valorTotal());
        assertEquals("12345678901", pagamentoRecord.cpf());
    }

    @Test
    public void testMapFromRecord() {
        // Arrange
        var clienteRecord = new ClienteRecord(0L, "45612345612", "teste@teste.com", "Teste");

        var cliente = new ClienteRecord(1L, "45612345612", "cliente@exemplo.com", "João Silva");
        var produto1 = new ProdutoRecord(1L, "Produto 1", "Descrição do Produto 1", 100.0, 10, "Disponível");
        var produto2 = new ProdutoRecord(2L, "Produto 2", "Descrição do Produto 2", 200.0, 5, "Disponível");
        List<ProdutoRecord> produtos = List.of(produto1, produto2);
        double total = produtos.stream().mapToDouble(produto -> produto.preco()).sum();
        var pedidoRecord = new PedidoRecord("PED123", cliente, produtos, total, "PENDENTE");

        // Act
        Pagamento pagamento = PagamentoMapper.mapFromRecord(pedidoRecord);

        // Assert
        assertEquals(0L, pagamento.getId());
        assertEquals("PED123", pagamento.getIdPedido());
        assertEquals(300.0, pagamento.getValorTotal());
        assertEquals("45612345612", pagamento.getCpf());
        assertEquals("PENDENTE", pagamento.getStatus());
    }
}
