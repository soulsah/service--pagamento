package br.com.fiap.postech.service_pagamento.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "idPedido não pode estar vazio")
    private String idPedido;

    @NotNull(message = "valorTotal não pode estar vazio")
    private double valorTotal;

    @NotNull(message = "cpf não pode estar vazio")
    private String cpf;

    @NotNull(message = "status não pode estar vazio")
    private String status;

    @NotNull(message = "qrcode não pode estar vazio")
    private String qr_data;

}
