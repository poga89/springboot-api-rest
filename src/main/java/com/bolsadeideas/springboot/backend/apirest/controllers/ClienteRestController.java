package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.backend.apirest.entity.Cliente;
import com.bolsadeideas.springboot.backend.apirest.services.IDclienteService;

@RestController
@CrossOrigin
@RequestMapping("/api") 
public class ClienteRestController {

 
	@Autowired
	private IDclienteService clienteservice;
	
	@GetMapping("/clientes")
	public List <Cliente> index(){
		return clienteservice.findAll();
	}
	
	/*
	@GetMapping("/clientes/{id}")
	public Cliente mostrar(@PathVariable Long id) {
		return clienteservice.findById(id);
	}
	*/
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> mostrar(@PathVariable Long id) {
		
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		try {
			cliente = clienteservice.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje","Error al conectar a la Base de Datos");
			response.put("error",e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		
		if(cliente == null) {
			response.put("mensaje","el cliente ID: ".concat(id.toString().concat(" no existe en la base de datos ")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND) ;
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK) ;
	}
	
	
	
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> cerate(@RequestBody Cliente cliente) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		Cliente clientenew = null;
		try {
			clientenew = clienteservice.save(cliente);
		} catch(DataAccessException e) {
			response.put("mensaje","Error al Insert de la Base de Datos");
			response.put("error",e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR) ;
		}
		
		response.put("mensaje", "El cliente fue creado con exito!");
		response.put("cliente", clientenew);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Cliente cliente,@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		
		Cliente clienteActual = clienteservice.findById(id);
		Cliente clienteupdate = null;
		
		if(clienteActual == null) {
			response.put("error","error no se pudo editar");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND) ;
		}
		
		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail() );
			clienteActual.setCreatAT(cliente.getCreatAT());
			clienteupdate = clienteservice.save(clienteActual);
		} catch (DataAccessException e) {
			response.put("error", "error al actualizar el usuario "+e.getMostSpecificCause());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "cliente actualizado correctamente!");
		response.put("cliente", clienteupdate);
		
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		try {
		clienteservice.delete(id);	
		} catch(DataAccessException e) {
			response.put("Error", "Error al eliminar usuario "+e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje","Usuario eliminado correctamente");
		return new ResponseEntity<Map<String, Object>>(response,HttpStatus.OK);
		
	}
	
	
}
