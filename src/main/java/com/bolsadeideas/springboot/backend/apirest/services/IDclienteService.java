package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;


public interface IDclienteService {
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pegable);
	
	public Cliente save(Cliente cliente);
	
	public void delete (Long id);
	
	public Cliente findById(Long id);

}
