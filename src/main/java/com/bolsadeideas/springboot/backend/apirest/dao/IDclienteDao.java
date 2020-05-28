package com.bolsadeideas.springboot.backend.apirest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.entity.Region;


public interface IDclienteDao extends JpaRepository<Cliente, Long>{

	@Query("from Region")
	public List<Region> findAllregiones();
	
}
