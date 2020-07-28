package com.example.curso.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.curso.entities.Cliente;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	@NotEmpty(message = "NOME tem que ser Preenchido")
	@Length(min = 5 , max = 120)
	private String nome;
	@NotEmpty(message = "EMAIL tem que ser Preenchido")
	@Email(message = "Email Invalido")
	private String email;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente c) {
		id = c.getId();
		nome = c.getNome();
		email = c.getEmail();
		
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
