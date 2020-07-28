package com.example.curso.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.curso.entities.Categoria;
import com.example.curso.entities.Produto;
import com.example.curso.exceptions.ObjectNotFoundException;
import com.example.curso.repositories.CategoriaRepository;
import com.example.curso.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository r;
	
	@Autowired
    private CategoriaRepository cr;
	
	public Produto find(Integer id) throws ObjectNotFoundException {
		Optional<Produto> obj = r.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
		
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy , String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = cr.findAllById(ids);
		return r.findDistinctByNomeContainingAndCategoriasIn(nome, categorias ,pageRequest);
	}
}
