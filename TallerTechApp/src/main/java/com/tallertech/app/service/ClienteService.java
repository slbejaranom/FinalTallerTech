package com.tallertech.app.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallertech.app.entity.Cliente;
import com.tallertech.app.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	public List<Cliente> findAllClientes(){
		return clienteRepository.findAll();
	}
	
	public Set<Cliente> findClienteByCedula(String cedula){
		return clienteRepository.findClientesByCedula(cedula);
	}
	public Optional<Cliente> findCliente(String Telefono){
		return clienteRepository.findById(Telefono);
	}
	
	public Cliente updateCliente(Cliente cliente) throws Exception {
		if(clienteRepository.findById(cliente.getTelefono()).isPresent()) {
			clienteRepository.save(cliente);
			return clienteRepository.findById(cliente.getTelefono()).get();
		}
		else {
			throw new Exception("No se encontró un cliente con ese teléfono");
		}			
	}
	
	public void saveCliente(Cliente cliente) throws Exception{
		if(cliente != null) {
			clienteRepository.save(cliente);
		}
		else {
			throw new Exception("El objeto cliente no puede estar vacío");
		}
	}
}