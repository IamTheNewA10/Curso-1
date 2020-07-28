package com.example.curso.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.domain.enums.EstadoPagamento;
import com.example.curso.entities.PagamentoBoleto;
import com.example.curso.entities.Pedido;
import com.example.curso.exceptions.ObjectNotFoundException;
import com.example.curso.repositories.PagamentoRepository;
import com.example.curso.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository r;
	
	@Autowired
	private BoletoService s;

	@Autowired
	private PagamentoRepository pr;
	
	public Pedido find(Integer id) throws ObjectNotFoundException {
		Optional<Pedido> obj = r.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		
	}
	
	public void insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPed(obj);
		if(obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagto = (PagamentoBoleto) obj.getPagamento();
			BoletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());																									
		}
		r.save(obj);
		pr.save(obj.getPagamento());
	}
}
