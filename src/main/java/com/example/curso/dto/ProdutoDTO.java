package com.example.curso.dto;

import java.io.Serializable;

import com.example.curso.entities.Cliente;
import com.example.curso.entities.Produto;

public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Double preco;
	
	public ProdutoDTO() {
	}
	
	public ProdutoDTO(Produto c) {
		id = c.getId();
		nome = c.getNome();
		preco = c.getPreco();
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPrice() {
		return preco;
	}
	public void setPrice(Double price) {
		this.preco = price;
	}
}
