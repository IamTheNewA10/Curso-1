package com.example.curso.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.example.curso.domain.enums.TipoCliente;
import com.example.curso.dto.ClienteDTO;
import com.example.curso.dto.ClienteNewDTO;
import com.example.curso.entities.Categoria;
import com.example.curso.entities.Cidade;
import com.example.curso.entities.Cliente;
import com.example.curso.entities.Endereco;
import com.example.curso.exceptions.DataIntegrityException;
import com.example.curso.exceptions.ObjectNotFoundException;
import com.example.curso.repositories.CidadeRepository;
import com.example.curso.repositories.ClienteRepository;
import com.example.curso.repositories.EnderecoRepository;

@Service
public class ClienteService {

	@Autowired
	private EnderecoRepository endR;
	
	@Autowired
	private CidadeRepository cidadeR;
	
	@Autowired
	private ClienteRepository r;

	public Cliente find(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> obj = r.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj , obj);
		return r.save(newObj);
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		
	}

	public void delete(Integer id) {
		find(id);
		try {
		r.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Nao e possivel deletar porque ha entidades relacionadas");
		}
	}
	public Page<Cliente> pageFinder(Integer page, Integer linesPerPage, String orderBy , String direction){
	    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
	    return r.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO cDTO) {
		return new Cliente(cDTO.getId(), cDTO.getNome(), cDTO.getEmail(), null , null);
	}
	
	public Cliente fromDTO(ClienteNewDTO cDTO) {
		Cliente cli = new Cliente(null, cDTO.getNome(), cDTO.getEmail(), cDTO.getCpfOuCnpj(), TipoCliente.toEnum(cDTO.getTipo()));
		Cidade cid = new Cidade(cDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, cDTO.getLogradouro(), cDTO.getNumero(), cDTO.getComplemento(), cDTO.getBairro(), cDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(cDTO.getTelefone1());
		
		if(cDTO.getTelefone2() != null) {
			cli.getTelefones().add(cDTO.getTelefone2());
		}
		
		if(cDTO.getTelefone3() != null) {
			cli.getTelefones().add(cDTO.getTelefone3());
		}
		
		return cli;
	}

	public List<Cliente> findAll() throws ObjectNotFoundException {
		return r.findAll();
	}
	@Transactional
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj = r.save(obj);
		endR.saveAll(obj.getEnderecos());
		return obj;
	}
}
