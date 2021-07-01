package com.tallertech.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.tallertech.app.entity.EmpresaTelefoniaFija;
import com.tallertech.app.service.EmpresaTelefoniaFijaService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api/empresaTelefoniaFija")
public class EmpresaTelefoniaFijaController {

	@Autowired
	EmpresaTelefoniaFijaService empresaTelefoniaFijaService;
	
	@GetMapping
	public List<EmpresaTelefoniaFija> getAll(){
		return empresaTelefoniaFijaService.findAllEmpresas();
	}
	
	@GetMapping("/{nit}")
	public EmpresaTelefoniaFija getByNit(@PathVariable String nit) {
		
		if(empresaTelefoniaFijaService.findEmpresaByNit(nit).isPresent()) {
			return empresaTelefoniaFijaService.findEmpresaByNit(nit).get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontró nada con este nit");
		}
		
	}
	
	@PostMapping
	public void insertEmpresa(@RequestBody EmpresaTelefoniaFija empresa) {
		// Se divide el nit en el guión
		String[] partes_nit = empresa.getNit().split("-");
		try {
			// Acá se mira si ambas partes del nit son números, sin letras
			Integer.parseInt(partes_nit[0]);
			Integer.parseInt(partes_nit[1]);
			
		} catch (NumberFormatException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Verifique el tipo de dato de todos los parámetros y que además estos existan en el Body que se envía.");
		} 	
				
	}
}