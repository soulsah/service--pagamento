package br.com.fiap.postech.service_pagamento.service.impl;

import br.com.fiap.postech.service_pagamento.entity.Pagamento;
import br.com.fiap.postech.service_pagamento.mapper.PagamentoMapper;
import br.com.fiap.postech.service_pagamento.records.PagamentoRecord;
import br.com.fiap.postech.service_pagamento.service.PagamentoService;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServiceImpl implements PagamentoService {

    @Override
    public void createPagamento(PagamentoRecord pagamentoRecord) {
        Pagamento pagamento = PagamentoMapper.mapFromRecord(pagamentoRecord);
        // integracao
    }

    @Override
    public void updateStatusPagamento(String idPagamento, String statusPagamento) {

    }

    @Override
    public PagamentoRecord findPagamentoById(String idPagamento) {
        return null;
    }

}
