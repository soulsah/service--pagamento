package br.com.fiap.postech.service_pagamento.repository;

import br.com.fiap.postech.service_pagamento.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, String> {
    Optional<Pagamento> findByIdPedido(String idPedido);
}
