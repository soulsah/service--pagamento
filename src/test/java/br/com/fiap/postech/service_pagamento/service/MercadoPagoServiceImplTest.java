package br.com.fiap.postech.service_pagamento.service.impl;

import br.com.fiap.postech.service_pagamento.mapper.OrderMapper;
import br.com.fiap.postech.service_pagamento.records.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class MercadoPagoServiceImplTest {

    @InjectMocks
    private MercadoPagoServiceImpl mercadoPagoService;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OrderMapper orderMapper;

    @Captor
    private ArgumentCaptor<HttpEntity<String>> httpEntityArgumentCaptor;

    private PedidoRecord pedidoRecord;
    private OrderRecord orderRecord;
    private MercadoPagoResponseRecord mercadoPagoResponseRecord;

    @BeforeEach
    public void setup() {
        pedidoRecord = obterPedidoRecordMock();
        orderRecord = obterOrderRecordMock();
        mercadoPagoResponseRecord = new MercadoPagoResponseRecord("id", "qrData"); // Inicializar com valores apropriados
    }

    @Test
    public void testCreateOrder_RestClientException() {
        when(orderMapper.mapFromRecord(pedidoRecord)).thenReturn(orderRecord);
        when(restTemplate.postForObject(
                anyString(),
                any(HttpEntity.class),
                eq(MercadoPagoResponseRecord.class))
        ).thenThrow(new RestClientException("Error"));

        assertThrows(RestClientException.class, () -> mercadoPagoService.createOrder(pedidoRecord));
    }

    private OrderRecord obterOrderRecordMock()
    {
        CashOutRecord cashOut = new CashOutRecord(100.0);
        ItemRecord item1 = new ItemRecord("Item A", 20.0, 2, "pcs", 40.0);
        ItemRecord item2 = new ItemRecord("Item B", 30.0, 1, "pcs", 30.0);
        List<ItemRecord> items = Arrays.asList(item1, item2);
        SponsorRecord sponsor = new SponsorRecord(12345);
        return new OrderRecord(
                cashOut,
                "Descrição do pedido",
                "REF123",
                items,
                "http://notification.url",
                sponsor,
                "Título do Pedido",
                70.0 // total_amount (a soma dos total_amount dos itens)
        );
    }

    private PedidoRecord obterPedidoRecordMock()
    {
        var cliente = new ClienteRecord(1L, "123.456.789-00", "cliente@exemplo.com", "João Silva");
        var produto1 = new ProdutoRecord(1L, "Produto 1", "Descrição do Produto 1", 100.0, 10, "Disponível");
        var produto2 = new ProdutoRecord(2L, "Produto 2", "Descrição do Produto 2", 200.0, 5, "Disponível");
        List<ProdutoRecord> produtos = List.of(produto1, produto2);
        double total = produtos.stream().mapToDouble(produto -> produto.preco()).sum();

        return new PedidoRecord("PED123", cliente, produtos, total, "Pendente");
    }
}
