package com.example.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.curso.dto.CategoriaDTO;
import com.example.curso.entities.Categoria;
import com.example.curso.exceptions.DataIntegrityException;
import com.example.curso.exceptions.ObjectNotFoundException;
import com.example.curso.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository r;

	public List<Categoria> findAll() throws ObjectNotFoundException {
		return r.findAll();
	}
	
	public Categoria find(Integer id) throws ObjectNotFoundException {
		Optional<Categoria> obj = r.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return r.save(obj);
	}

	public Categoria update(Categoria obj) {
		find(obj.getId());
		return r.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
		r.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao e possivel deletar uma categoria que possui produtos");
		}
	}
	public Page<Categoria> pageFinder(Integer page, Integer linesPerPage, String orderBy , String direction){
	    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
	    return r.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO cDTO) {
		return new Categoria(cDTO.getId(), cDTO.getNome());
	}
}
