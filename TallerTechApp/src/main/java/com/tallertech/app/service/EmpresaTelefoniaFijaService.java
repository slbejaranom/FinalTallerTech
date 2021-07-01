package com.tallertech.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tallertech.app.entity.EmpresaTelefoniaFija;
import com.tallertech.app.repository.EmpresaTelefoniaFijaRepository;

@Service
public class EmpresaTelefoniaFijaService {

	@Autowired
	EmpresaTelefoniaFijaRepository empresaTelefoniaFijaRepository;
	
	public List<EmpresaTelefoniaFija> findAllEmpresas(){
		return empresaTelefoniaFijaRepository.findAll();
	}
	
	public Optional<EmpresaTelefoniaFija> findEmpresaByNit(String nit){
		Optional<EmpresaTelefoniaFija> empresaTelefoniaFija = empresaTelefoniaFijaRepository.findById(nit);
		return empresaTelefoniaFija;
	}
	
	public void saveEmpresa(EmpresaTelefoniaFija empresaTelefoniaFija) throws Exception{
		if(empresaTelefoniaFija != null) {
			empresaTelefoniaFijaRepository.save(empresaTelefoniaFija);
		}
		else {
			throw new Exception("Debe haber un objeto empresa");
		}
	}
	
	public void deleteEmpresa(String nit) throws Exception{
		if(empresaTelefoniaFijaRepository.findById(nit).isPresent()) {
			Optional<EmpresaTelefoniaFija> empresaParaBorrar = empresaTelefoniaFijaRepository.findById(nit);
			empresaParaBorrar.get().setEsta_activo('0');
		}
		else {
			throw new Exception("No se encontró empresa con ese NIT");
		}
	}
	
	public EmpresaTelefoniaFija updateEmpresa(EmpresaTelefoniaFija empresaTelefoniaFija) throws Exception {
		String nit = empresaTelefoniaFija.getNit();
		
		if(empresaTelefoniaFijaRepository.findById(nit).isPresent()) {
			empresaTelefoniaFijaRepository.save(empresaTelefoniaFija);
			return empresaTelefoniaFijaRepository.findById(nit).get();
		}
		else {
			throw new Exception("No se encontró empresa con ese NIT");
		}
	}
}
