package com.tallertech.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name="NIT", nullable = false)
	EmpresaTelefoniaFija empresaTelefoniaFija;

	@ManyToOne
	@JoinColumn(name="TELEFONO", nullable = false)
	Cliente cliente;
	
	public ClienteEmpresa() {
		super();
	}


	public ClienteEmpresa(long idRegistro, char estaActivo, Cliente telefono,
			EmpresaTelefoniaFija empresaTelefoniaFija) {
		super();
		this.idRegistro = idRegistro;
		this.estaActivo = estaActivo;
		this.cliente = telefono;
		this.empresaTelefoniaFija = empresaTelefoniaFija;
	}


	public long getIdRegistro() {
		return idRegistro;
	}


	public void setIdRegistro(long idRegistro) {
		this.idRegistro = idRegistro;
	}


	public char getEstaActivo() {
		return estaActivo;
	}


	public void setEstaActivo(char estaActivo) {
		this.estaActivo = estaActivo;
	}


	public Cliente getTelefono() {
		return cliente;
	}


	public void setTelefono(Cliente telefono) {
		this.cliente = telefono;
	}


	public EmpresaTelefoniaFija getEmpresaTelefoniaFija() {
		return empresaTelefoniaFija;
	}


	public void setEmpresaTelefoniaFija(EmpresaTelefoniaFija empresaTelefoniaFija) {
		this.empresaTelefoniaFija = empresaTelefoniaFija;
	}	
	
}