package com.tallertech.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name="CLIENTE_EMPRESA")
public class ClienteEmpresa {
	
	@Id
	@Column(name="ID_REGISTRO", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long idRegistro;
	
	@Column(name="ESTA_ACTIVO", nullable = false)
	char estaActivo;
	
	@Column(name="TELEFONO", nullable = false)
	String telefono;
	
	
}