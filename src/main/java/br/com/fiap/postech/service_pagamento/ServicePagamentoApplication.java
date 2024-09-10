package br.com.fiap.postech.service_pagamento;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ServicePagamentoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicePagamentoApplication.class, args);
	}

}
