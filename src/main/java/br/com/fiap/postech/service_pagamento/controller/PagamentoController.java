package br.com.fiap.postech.service_pagamento.controller;

import br.com.fiap.postech.service_pagamento.records.PedidoRecord;
import br.com.fiap.postech.service_pagamento.records.WebhookRecord;
import br.com.fiap.postech.service_pagamento.repository.PagamentoQueueProducer;
import br.com.fiap.postech.service_pagamento.service.MercadoPagoService;
import br.com.fiap.postech.service_pagamento.service.PagamentoService;
import br.com.fiap.postech.service_pagamento.service.ProducaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @Autowired
    private ProducaoService producaoService;

    @Autowired
    private PagamentoQueueProducer pagamentoQueueProducer;

    @PostMapping("")
    public ResponseEntity<String> createPagamento(@RequestBody PedidoRecord pedidoRecord){
        var response = mercadoPagoService.createOrder(pedidoRecord);
        pagamentoService.createPagamento(pedidoRecord, response.qr_data());

        return ResponseEntity.ok().body(response.qr_data());
    }

    @PostMapping("webhook/{pedidoId}")
    public HttpStatus updatePagamento(@RequestBody WebhookRecord webhookRecord, @RequestParam String pedidoId){
        producaoService.notificarPagamento(pedidoId);
        pagamentoService.updateStatusPagamento(pedidoId, "PAGO");

        return HttpStatus.OK;
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<String> getPagamentos(@PathVariable String pedidoId)
    {
        var response = pagamentoService.findPagamentoByIdPedido(pedidoId);
        return ResponseEntity.ok().body(response);
    }

}
