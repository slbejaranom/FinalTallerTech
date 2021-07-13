package com.tallertech.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tallertech.app.entity.ClienteEmpresa;
import com.tallertech.app.service.ClienteEmpresaService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/clienteEmpresa")
public class ClienteEmpresaController {

	@Autowired
	ClienteEmpresaService clienteEmpresaService;
	
	@GetMapping
	public List<ClienteEmpresa> getAll(){
		return clienteEmpresaService.findAllConvenios();
	}
	
	@GetMapping("/registro/{id}")
	public ClienteEmpresa getById(@PathVariable long id) {
		
		if(clienteEmpresaService.findConvenioById(id).isPresent()) {
			return clienteEmpresaService.findConvenioById(id).get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontr� nada con este nit");
		}
		
	}
	
	@PostMapping
	public void insertConvenio(@RequestBody ClienteEmpresa convenio) {
		// Se divide el nit en el gui�n
		String[] partes_nit = convenio.getEmpresaTelefoniaFija().getNit().split("-");
		try {
			// Ac� se mira si ambas partes del nit son n�meros, sin letras
			Integer.parseInt(partes_nit[0]);
			Integer.parseInt(partes_nit[1]);
			
		} catch (NumberFormatException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Verifique el tipo de dato de todos los par�metros y que adem�s estos existan en el Body que se env�a.");
		} 	
				
	}
	
	@GetMapping("/nit/{nit}")
	public List<ClienteEmpresa> getConveniosConNit(@PathVariable String nit){
		return clienteEmpresaService.findConveniosConNit(nit);
	}
	
	@DeleteMapping("/registro/{idRegistro}")
	public void deleteConvenio(@PathVariable long idRegistro) {
		if(clienteEmpresaService.findConvenioById(idRegistro).isPresent()) {
			ClienteEmpresa aBorrar = clienteEmpresaService.findConvenioById(idRegistro).get();
			aBorrar.setEstaActivo('0');
			clienteEmpresaService.saveConvenio(aBorrar);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontr� nada con este nit");
		}
	}
}
