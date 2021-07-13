package com.tallertech.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallertech.app.entity.Llamada;
import com.tallertech.app.repository.LlamadaRepository;

@Service
public class LlamadaService {

	@Autowired
	LlamadaRepository llamadaRepository;
	
	public List<Llamada> getAllLlamadas(){
		return llamadaRepository.findAll();
	}
	
	public Optional<Llamada> getLlamadaById(long id){
		return llamadaRepository.findById(id);
	}
	
	public Llamada updateLlamada(Llamada llamada) throws Exception{
		if(llamadaRepository.findById(llamada.getIdLlamada()).isPresent()) {
			llamadaRepository.save(llamada);
			return llamadaRepository.findById(llamada.getIdLlamada()).get();
		}else {
			throw new Exception("No hay una llamada con este ID");
		}
	}
	
	public void saveLlamada(Llamada llamada) throws Exception{
		if(llamada != null) {
			llamadaRepository.save(llamada);
		}else {
			throw new Exception("El oobjeto llamada no puede ser nulo");
		}
	}
}
