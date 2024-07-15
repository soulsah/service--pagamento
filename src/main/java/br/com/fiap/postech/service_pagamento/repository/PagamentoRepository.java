package br.com.fiap.postech.service_pagamento.repository;

import br.com.fiap.postech.service_pagamento.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
