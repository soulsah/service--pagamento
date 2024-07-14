package br.com.fiap.postech.service_pagamento.controller;

import br.com.fiap.postech.service_pagamento.records.PagamentoRecord;
import br.com.fiap.postech.service_pagamento.records.PedidoRecord;
import br.com.fiap.postech.service_pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping("")
    public HttpStatus createPagamento(@RequestBody PedidoRecord pedido){

        return HttpStatus.ACCEPTED;
    }

}
