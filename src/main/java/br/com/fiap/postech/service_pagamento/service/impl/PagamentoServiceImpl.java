package br.com.fiap.postech.service_pagamento.service.impl;

import br.com.fiap.postech.service_pagamento.entity.Pagamento;
import br.com.fiap.postech.service_pagamento.mapper.PagamentoMapper;
import br.com.fiap.postech.service_pagamento.records.PagamentoRecord;
import br.com.fiap.postech.service_pagamento.records.PedidoRecord;
import br.com.fiap.postech.service_pagamento.repository.PagamentoRepository;
import br.com.fiap.postech.service_pagamento.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public void createPagamento(PedidoRecord pedidoRecord) {
        Pagamento pagamento = PagamentoMapper.mapFromRecord(pedidoRecord);
        pagamentoRepository.save(pagamento);
    }

    @Override
    public void updateStatusPagamento(String idPedido, String statusPagamento) {
        var pagamentoOptional = pagamentoRepository.findByIdPedido(idPedido);
        if (pagamentoOptional.isPresent()) {
            pagamentoOptional.get().setStatus(statusPagamento);
            pagamentoRepository.save(pagamentoOptional.get());
        }
    }

    @Override
    public PagamentoRecord findPagamentoById(String idPagamento) {
        return null;
    }

}
