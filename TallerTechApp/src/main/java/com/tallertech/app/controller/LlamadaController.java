package com.tallertech.app.controller;

import java.util.List;

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

import com.tallertech.app.entity.Llamada;
import com.tallertech.app.service.LlamadaService;

@RestController
@RequestMapping("/api/llamada")
@CrossOrigin(origins="*")
public class LlamadaController {

	@Autowired
	LlamadaService llamadaService;
	
	@GetMapping
	public List<Llamada> getAllLlamadas(){
		return llamadaService.getAllLlamadas();
	}
	
	@PostMapping
	public void postLlamada(Llamada llamada) throws Exception{
		if(llamadaService.getLlamadaById(llamada.getIdLlamada()).isPresent()) {
			throw new ResponseStatusException(HttpStatus.CONFLICT,"La llamada con ese ID ya existe");
		}else {
			try {
				Integer.parseInt(llamada.getTelefonoDestino());
			}catch(NumberFormatException ex) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Revise el formato y tipo de dato de los datos envíados al servidor.");
			}
			
			llamadaService.saveLlamada(llamada);
		}
	}
	
	@PutMapping("/{id}")
	public Llamada updateLlamada(@PathVariable long id, @RequestBody Llamada llamada) throws Exception {
		if(llamadaService.getLlamadaById(id).isPresent()) {
			llamadaService.updateLlamada(llamada);
			return llamadaService.getLlamadaById(llamada.getIdLlamada()).get();
		}else {
			throw new ResponseStatusException(HttpStatus.CONFLICT,"La llamada con ese ID ya existe");
		}
	}
}
