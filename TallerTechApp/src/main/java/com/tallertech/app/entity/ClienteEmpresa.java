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
	
	@Column(name="TELEFONO", nullable = false)
	String telefono;
	
	@ManyToOne
	@JoinColumn(name="NIT", nullable = false)
	private EmpresaTelefoniaFija empresaTelefoniaFija;

	
	public ClienteEmpresa() {
		super();
	}


	public ClienteEmpresa(long idRegistro, char estaActivo, String telefono,
			EmpresaTelefoniaFija empresaTelefoniaFija) {
		super();
		this.idRegistro = idRegistro;
		this.estaActivo = estaActivo;
		this.telefono = telefono;
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


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public EmpresaTelefoniaFija getEmpresaTelefoniaFija() {
		return empresaTelefoniaFija;
	}


	public void setEmpresaTelefoniaFija(EmpresaTelefoniaFija empresaTelefoniaFija) {
		this.empresaTelefoniaFija = empresaTelefoniaFija;
	}
	
	
	
}