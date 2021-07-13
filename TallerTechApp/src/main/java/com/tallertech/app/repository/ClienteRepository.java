package com.tallertech.app.repository;

import java.util.Set;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tallertech.app.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{

	@Query("select clientes from CLIENTE where clientes.cedula=?1")
	Set<Cliente> findClientesByCedula(String cedula);
}