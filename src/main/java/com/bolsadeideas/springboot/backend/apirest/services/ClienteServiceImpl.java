package com.bolsadeideas.springboot.backend.apirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.dao.IDclienteDao;
import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;

@Service
public class ClienteServiceImpl implements IDclienteService {

	
	@Autowired
	private IDclienteDao clienteDao;
	
	@Override
	@Transactional
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return (List<Cliente>) clienteDao.findAll();
	}

	@Override
	@Transactional
	public Page<Cliente> findAll(Pageable pegable) {
		// TODO Auto-generated method stub
		return clienteDao.findAll(pegable);
	}
	
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		// TODO Auto-generated method stub
		return clienteDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		clienteDao.deleteById(id);
	}

	@Override
	@Transactional
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		return clienteDao.findById(id).orElse(null) ;
	}

	

}
