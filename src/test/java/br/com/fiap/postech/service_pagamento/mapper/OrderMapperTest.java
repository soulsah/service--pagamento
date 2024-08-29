package br.com.fiap.postech.service_pagamento.mapper;

import br.com.fiap.postech.service_pagamento.records.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderMapperTest {

    @InjectMocks
    private OrderMapper orderMapper;

    @Mock
    private PedidoRecord pedidoRecord;

    @Mock
    private ProdutoRecord produtoRecord;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(orderMapper, "descriptionTexto", "descricao");
        ReflectionTestUtils.setField(orderMapper, "notificationUrl", "https://webhook-test.com/");
        ReflectionTestUtils.setField(orderMapper, "sponsor", "189254301");
        ReflectionTestUtils.setField(orderMapper, "titleTexto", "titulo");

        when(pedidoRecord.pedidoId()).thenReturn("12345");
        when(pedidoRecord.produtosIds()).thenReturn(List.of(produtoRecord));
        when(pedidoRecord.total()).thenReturn(100.0);

        when(produtoRecord.nome()).thenReturn("Produto 1");
        when(produtoRecord.preco()).thenReturn(10.0);
        when(produtoRecord.quantidadeEstoque()).thenReturn(5);
    }

//    @Test
//    void testMapFromRecord() {
//        OrderRecord orderRecord = orderMapper.mapFromRecord(pedidoRecord);
//
//        assertEquals("descricao", orderRecord.description());
//        assertEquals("https://webhook-test.com/cb0bb40aacd20a8f9e5833202d5729ca", orderRecord.notification_url());
//        assertEquals(1, orderRecord.items().size());
//        assertEquals("titulo", orderRecord.title());
//        assertEquals(100.0, orderRecord.total_amount());
//    }
}
