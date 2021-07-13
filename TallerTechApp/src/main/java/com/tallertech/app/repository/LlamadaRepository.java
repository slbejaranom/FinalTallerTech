package com.tallertech.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallertech.app.entity.Llamada;

@Repository
public interface LlamadaRepository extends JpaRepository<Llamada, Long>{
	
}