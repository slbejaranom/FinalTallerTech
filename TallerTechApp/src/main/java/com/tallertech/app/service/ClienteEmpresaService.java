package com.tallertech.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.tallertech.app.entity.ClienteEmpresa;
import com.tallertech.app.repository.ClienteEmpresaRepository;

public class ClienteEmpresaService {
	
	@Autowired
	ClienteEmpresaRepository clienteEmpresaRepository;
	
	public List<ClienteEmpresa> findConveniosConNit(String nit){
		return clienteEmpresaRepository.findConveniosConNit(nit);
	}
	public List<ClienteEmpresa> findAllConvenios(){
		return clienteEmpresaRepository.findAll();
	}
	
	public Optional<ClienteEmpresa> findConvenioById(long id){
		Optional<ClienteEmpresa> convenio = clienteEmpresaRepository.findById(id);
		return convenio;
	}
	
	public void saveConvenio(ClienteEmpresa nuevoConvenio) throws NullPointerException{
		if(nuevoConvenio!=null) {
			clienteEmpresaRepository.save(nuevoConvenio);
		}else {
			throw new NullPointerException("No hay un objeto nuevoConvenio definido para el método");
		}
	}
	
	public void deleteConvenio(long id) throws Exception{
		if(clienteEmpresaRepository.findById(id).isPresent()) {
			ClienteEmpresa convenioABorrar = clienteEmpresaRepository.findById(id).get();
			convenioABorrar.setEstaActivo('0');
			clienteEmpresaRepository.save(convenioABorrar);
		}else {
			throw new Exception("No se encuentra un convenio con ese id");
		}
	}
	
	public ClienteEmpresa updateConvenio(ClienteEmpresa convenio) throws Exception{
		if(clienteEmpresaRepository.findById(convenio.getIdRegistro()).isPresent()) {
			clienteEmpresaRepository.save(convenio);
			return convenio;
		}else {
			throw new Exception("No se encuentra un convenio con ese id");
		}
	}
}
