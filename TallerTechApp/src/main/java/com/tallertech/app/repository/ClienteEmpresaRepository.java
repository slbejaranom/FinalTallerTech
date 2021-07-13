package com.tallertech.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tallertech.app.entity.ClienteEmpresa;

@Repository
public interface ClienteEmpresaRepository extends JpaRepository<ClienteEmpresa, Long>{
	
	@Query("select b from ClienteEmpresa b where b.empresaTelefoniaFija.nit=?1")
	List<ClienteEmpresa> findConveniosConNit(String nit);
}