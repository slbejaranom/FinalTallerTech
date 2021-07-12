package com.tallertech.app.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallertech.app.entity.ClienteEmpresa;

@Repository
public interface ClienteEmpresaRepository extends JpaRepository<ClienteEmpresa, Long>{
	
	@Query("select convenios from ClienteEmpresa where convenios.NIT = ?1")
	List<ClienteEmpresa> findConveniosConNit(String nit);
}