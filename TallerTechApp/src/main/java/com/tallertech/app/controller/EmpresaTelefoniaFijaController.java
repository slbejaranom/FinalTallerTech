package com.tallertech.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/count")
	public int contarConvenios() {
		return empresaTelefoniaFijaService.getNumberOfEmpresas();
	}
	
	@PostMapping
	public void insertEmpresa(@RequestBody EmpresaTelefoniaFija empresa) throws Exception{
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
		//Ahora se revisa que no esté repetido ninguno de los parámetros
		boolean condicion1 = (empresa.getPrimer_param_archivo() == empresa.getSegundo_param_archivo()) || (empresa.getPrimer_param_archivo() == empresa.getTercer_param_archivo()) || (empresa.getPrimer_param_archivo() == empresa.getCuarto_param_archivo()) || (empresa.getPrimer_param_archivo() == empresa.getQuinto_param_archivo());
		boolean condicion2 = (empresa.getPrimer_param_archivo() == empresa.getSegundo_param_archivo()) || (empresa.getSegundo_param_archivo() == empresa.getTercer_param_archivo()) || (empresa.getSegundo_param_archivo() == empresa.getCuarto_param_archivo()) || (empresa.getSegundo_param_archivo() == empresa.getQuinto_param_archivo());
		boolean condicion3 = (empresa.getPrimer_param_archivo() == empresa.getTercer_param_archivo()) || (empresa.getSegundo_param_archivo() == empresa.getTercer_param_archivo()) || (empresa.getTercer_param_archivo() == empresa.getCuarto_param_archivo()) || (empresa.getTercer_param_archivo() == empresa.getQuinto_param_archivo());
		boolean condicion4 = (empresa.getPrimer_param_archivo() == empresa.getCuarto_param_archivo()) || (empresa.getSegundo_param_archivo() == empresa.getCuarto_param_archivo()) || (empresa.getTercer_param_archivo() == empresa.getCuarto_param_archivo()) || (empresa.getCuarto_param_archivo() == empresa.getQuinto_param_archivo());
		boolean condicion5 = (empresa.getPrimer_param_archivo() == empresa.getQuinto_param_archivo()) || (empresa.getSegundo_param_archivo() == empresa.getQuinto_param_archivo()) || (empresa.getTercer_param_archivo() == empresa.getQuinto_param_archivo()) || (empresa.getCuarto_param_archivo() == empresa.getQuinto_param_archivo());
		if(condicion1 || condicion2 || condicion3 || condicion4 || condicion5) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Verifique el tipo de dato de todos los parámetros y que además estos existan en el Body que se envía.");
		}else {
			if(empresaTelefoniaFijaService.findEmpresaByNit(empresa.getNit()).isPresent()) {
				throw new ResponseStatusException(HttpStatus.CONFLICT,
						"Ya existe un convenio registrado con ese NIT.");
			}else {
				empresaTelefoniaFijaService.saveEmpresa(empresa);
			}
		}
	}
	
	@DeleteMapping("/{nit}")
	public void deleteEmpresa(@PathVariable String nit) throws Exception{
		if(empresaTelefoniaFijaService.findEmpresaByNit(nit).isPresent()) {
			EmpresaTelefoniaFija aBorrar = empresaTelefoniaFijaService.findEmpresaByNit(nit).get();
			aBorrar.setEsta_activo('0');
			empresaTelefoniaFijaService.saveEmpresa(aBorrar);
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontró nada con este nit");
		}		
	}
	
	@PutMapping("/{nit}")
	public EmpresaTelefoniaFija actualizarEmpresa(@PathVariable String nit, @RequestBody EmpresaTelefoniaFija empresa) throws Exception{
		if(empresaTelefoniaFijaService.findEmpresaByNit(nit).isPresent()) {
			EmpresaTelefoniaFija aActualizar = empresaTelefoniaFijaService.findEmpresaByNit(nit).get();
			aActualizar = empresa;
			empresaTelefoniaFijaService.saveEmpresa(aActualizar);
			return empresaTelefoniaFijaService.findEmpresaByNit(aActualizar.getNit()).get();
		}
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No se encontró nada con este nit");
		}		
	}
}