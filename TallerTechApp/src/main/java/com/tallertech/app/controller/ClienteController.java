package com.tallertech.app.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tallertech.app.entity.Cliente;
import com.tallertech.app.repository.ClienteRepository;
import com.tallertech.app.service.ClienteService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin(origins="*")
public class ClienteController {

	@Autowired
	ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> getAllClientes(){
		return clienteService.findAllClientes();
	}
	
	@GetMapping("/{telefono}")
	public Cliente getClienteByTelefono(@PathVariable String telefono) {
		if(clienteService.findCliente(telefono).isPresent()) {
			return clienteService.findCliente(telefono).get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontro´cliente con este teléfono");
		}
	}
	
	@GetMapping("/cedula/{cedula}")
	public Set<Cliente> getClienteByCedula(@PathVariable String cedula) {
		if(clienteService.findClienteByCedula(cedula).size() > 0) {
			return clienteService.findClienteByCedula(cedula);
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontró cliente con esta cédula");
		}
	}
	
	@PostMapping
	public void saveCliente(Cliente cliente) throws Exception{
		if(clienteService.findCliente(cliente.getTelefono()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un cliente con este número de teléfono");
		}else {
			
			
			try {
				Integer.parseInt(cliente.getTelefono());
				Integer.parseInt(cliente.getCedula());
			}catch(NumberFormatException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Revise el formato de los datos");
			}
			
			clienteService.saveCliente(cliente);
		}
	}
	
	@PutMapping("/{telefono}")
	public Cliente updateCliente(@PathVariable String telefono, @RequestBody Cliente cliente) throws Exception{
		if(clienteService.findCliente(telefono).isPresent()) {
			try {
				Integer.parseInt(cliente.getTelefono());
				Integer.parseInt(cliente.getCedula());
			}catch(NumberFormatException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Revise el formato de los datos");
			}
			clienteService.saveCliente(cliente);
			return clienteService.findCliente(telefono).get();
		}else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontró un cliente con este número de teléfono");
		}
	}
}