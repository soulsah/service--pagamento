package br.com.fiap.postech.service_pagamento.service;

import br.com.fiap.postech.service_pagamento.records.ProducaoRecord;
import br.com.fiap.postech.service_pagamento.records.WebhookRecord;

public interface ProducaoService {

    void notificarPagamento(String pedidoId);

}
