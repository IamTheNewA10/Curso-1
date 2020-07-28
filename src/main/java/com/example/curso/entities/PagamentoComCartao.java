package com.example.curso.entities;

import javax.persistence.Entity;

import com.example.curso.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {

	private Integer numeroParcelas;
	
	public PagamentoComCartao() {
		
	}
	
	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido ped, Integer numeroParcelas) {
		super(id, estadoPagamento, ped);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	
	
}
