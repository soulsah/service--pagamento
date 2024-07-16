package br.com.fiap.postech.service_pagamento.controller;

import br.com.fiap.postech.service_pagamento.records.*;
import br.com.fiap.postech.service_pagamento.service.MercadoPagoService;
import br.com.fiap.postech.service_pagamento.service.PagamentoService;
import br.com.fiap.postech.service_pagamento.service.ProducaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagamentoControllerTest {

    @InjectMocks
    private PagamentoController pagamentoController;

    @Mock
    private PagamentoService pagamentoService;

    @Mock
    private MercadoPagoService mercadoPagoService;

    @Mock
    private ProducaoService producaoService;

    private PedidoRecord pedidoRecord;
    private WebhookRecord webhookRecord;

    @BeforeEach
    void setUp() {
        var cliente = new ClienteRecord(1L, "123.456.789-00", "cliente@exemplo.com", "João Silva");
        var produto1 = new ProdutoRecord(1L, "Produto 1", "Descrição do Produto 1", 100.0, 10, "Disponível");
        var produto2 = new ProdutoRecord(2L, "Produto 2", "Descrição do Produto 2", 200.0, 5, "Disponível");
        List<ProdutoRecord> produtos = List.of(produto1, produto2);
        double total = produtos.stream().mapToDouble(produto -> produto.preco()).sum();

        pedidoRecord = new PedidoRecord("PED123", cliente, produtos, total, "Pendente");
        webhookRecord = new WebhookRecord("resource", "topic");
    }

    @Test
    void createPagamento_success() {
        // Arrange
        var qrData = "testQrData";
        when(mercadoPagoService.createOrder(any(PedidoRecord.class)))
                .thenReturn(mock(MercadoPagoResponseRecord.class));
        // Act
        ResponseEntity<String> response = pagamentoController.createPagamento(pedidoRecord);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(mercadoPagoService).createOrder(pedidoRecord);
        verify(pagamentoService).createPagamento(pedidoRecord);
    }

    @Test
    void updatePagamento_success() {
        // Arrange
        String pedidoId = "testPedidoId";

        // Act
        HttpStatus status = pagamentoController.updatePagamento(webhookRecord, pedidoId);

        // Assert
        assertEquals(HttpStatus.OK, status);
        verify(producaoService).notificarPagamento(pedidoId);
        verify(pagamentoService).updateStatusPagamento(pedidoId, "PAGO");
    }
}
