package br.com.fiap.postech.service_pagamento.controller;

import br.com.fiap.postech.service_pagamento.records.PedidoRecord;
import br.com.fiap.postech.service_pagamento.service.MercadoPagoService;
import br.com.fiap.postech.service_pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("")
    public ResponseEntity<String> createPagamento(@RequestBody PedidoRecord pedidoRecord){
        var response = mercadoPagoService.createOrder(pedidoRecord);
        pagamentoService.createPagamento(pedidoRecord);

        return ResponseEntity.ok().body(response.qr_data());
    }

}
