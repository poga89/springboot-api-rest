package com.bolsadeideas.springboot.backend.apirest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;


public interface IDclienteDao extends JpaRepository<Cliente, Long>{

}
