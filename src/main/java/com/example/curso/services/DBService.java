package com.example.curso.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.curso.domain.enums.EstadoPagamento;
import com.example.curso.domain.enums.TipoCliente;
import com.example.curso.entities.Categoria;
import com.example.curso.entities.Cidade;
import com.example.curso.entities.Cliente;
import com.example.curso.entities.Endereco;
import com.example.curso.entities.Estado;
import com.example.curso.entities.ItemPedido;
import com.example.curso.entities.Pagamento;
import com.example.curso.entities.PagamentoBoleto;
import com.example.curso.entities.PagamentoComCartao;
import com.example.curso.entities.Pedido;
import com.example.curso.entities.Produto;
import com.example.curso.repositories.CategoriaRepository;
import com.example.curso.repositories.CidadeRepository;
import com.example.curso.repositories.ClienteRepository;
import com.example.curso.repositories.EnderecoRepository;
import com.example.curso.repositories.EstadoRepository;
import com.example.curso.repositories.ItemPedidoRepository;
import com.example.curso.repositories.PagamentoRepository;
import com.example.curso.repositories.PedidoRepository;
import com.example.curso.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private ClienteRepository cltr;

	@Autowired
	private EnderecoRepository endr;

	@Autowired
	private CidadeRepository cdr;

	@Autowired
	private EstadoRepository er;

	@Autowired
	private CategoriaRepository cr;

	@Autowired
	private ProdutoRepository pr;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository IPR;
	
	public void instantiateTestDataBase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Escritorio");
		Categoria cat2 = new Categoria(null, "Informatica");
		Categoria cat3 = new Categoria(null, "Games");
		Categoria cat4 = new Categoria(null, "Televisores");
		Categoria cat5 = new Categoria(null, "Casa Mesa e Banho");
        Categoria cat6 = new Categoria(null, "Eletrodomesticos");
        Categoria cat7 = new Categoria(null, "Perfumaria");
        Categoria cat8 = new Categoria(null, "Moveis");

		Produto p1 = new Produto(null, "computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 90.00);
		Produto p4 = new Produto(null, "God of War", 100.00);
		Produto p5 = new Produto(null, "Mesa De Escritorio", 300.00);
		Produto p6 = new Produto(null, "The Last Of Us part 2", 200.00);
		Produto p7 = new Produto(null, "Toalha", 30.00);
		Produto p8 = new Produto(null, "Fogao 6 bocas", 500.00);
		Produto p9 = new Produto(null, "TV LG 50", 1500.00);
		Produto p10 = new Produto(null, "Colcha", 25.00);
		Produto p11 = new Produto(null, "Shampoo", 7.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3, p5));
		cat2.getProdutos().addAll(Arrays.asList(p1, p3));
		cat3.getProdutos().addAll(Arrays.asList(p4, p6));
		cat4.getProdutos().addAll(Arrays.asList(p9));
		cat5.getProdutos().addAll(Arrays.asList(p7, p10));
		cat6.getProdutos().addAll(Arrays.asList(p8));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p2.getCategorias().addAll(Arrays.asList(cat1));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p4.getCategorias().addAll(Arrays.asList(cat3));
		p5.getCategorias().addAll(Arrays.asList(cat1));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat5));
		p8.getCategorias().addAll(Arrays.asList(cat6));
		p9.getCategorias().addAll(Arrays.asList(cat4));
		p10.getCategorias().addAll(Arrays.asList(cat5));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		cr.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8));
		pr.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado est1 = new Estado(null, "Rio De Janeiro");
		Estado est2 = new Estado(null, "Sao Paulo");

		Cidade c1 = new Cidade(null, "Areal", est1);
		Cidade c2 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));

		er.saveAll(Arrays.asList(est1, est2));
		cdr.saveAll(Arrays.asList(c1, c2));

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 301", "Jardia", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "3877012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		cltr.saveAll(Arrays.asList(cli1));

		endr.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2019 19:35"), cli1, e2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoBoleto(null , EstadoPagamento.PENDENTE , ped2 , sdf.parse("20/08/2020 20:48") , sdf.parse("20/07/2020 18:34"));
		ped2.setPagamento(pagto2);

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
	    ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	    
	    ped1.getItems().addAll(Arrays.asList(ip1 , ip2));
	    ped2.getItems().addAll(Arrays.asList(ip3));
	    
	    p1.getItems().addAll(Arrays.asList(ip1));
	    p2.getItems().addAll(Arrays.asList(ip3));
	    p3.getItems().addAll(Arrays.asList(ip2));
	    
	    IPR.saveAll(Arrays.asList(ip1 , ip2 , ip3));
	}
}
