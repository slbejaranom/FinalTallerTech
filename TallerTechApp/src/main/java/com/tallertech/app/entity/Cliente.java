package com.tallertech.app.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CLIENTE")
public class Cliente {

	@Id
	@Column(name="TELEFONO", nullable=false)
	String telefono;
	
	@Column(name="CEDULA", nullable=false)
	String cedula;
	
	@Column(name="PRIMER_NOMBRE", nullable=false)
	String primer_nombre;
	
	@Column(name="SEGUNDO_NOMBRE", nullable=false)
	String segundo_nombre;
	
	@Column(name="PRIMER_APELLIDO", nullable=false)
	String primer_apellido;
	
	@Column(name="SEGUNDO_apellido", nullable=false)
	String segundo_apellido;
	
	@JsonIgnore
	@OneToMany(targetEntity=ClienteEmpresa.class ,cascade = CascadeType.ALL, mappedBy="cliente")
	Set<ClienteEmpresa> convenios;
	
	public Cliente() {
		super();
	}

	public Cliente(String telefono, String cedula, String primer_nombre, String segundo_nombre, String primer_apellido,
			String segundo_apellido) {
		super();
		this.telefono = telefono;
		this.cedula = cedula;
		this.primer_nombre = primer_nombre;
		this.segundo_nombre = segundo_nombre;
		this.primer_apellido = primer_apellido;
		this.segundo_apellido = segundo_apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getPrimer_nombre() {
		return primer_nombre;
	}

	public void setPrimer_nombre(String primer_nombre) {
		this.primer_nombre = primer_nombre;
	}

	public String getSegundo_nombre() {
		return segundo_nombre;
	}

	public void setSegundo_nombre(String segundo_nombre) {
		this.segundo_nombre = segundo_nombre;
	}

	public String getPrimer_apellido() {
		return primer_apellido;
	}

	public void setPrimer_apellido(String primer_apellido) {
		this.primer_apellido = primer_apellido;
	}

	public String getSegundo_apellido() {
		return segundo_apellido;
	}

	public void setSegundo_apellido(String segundo_apellido) {
		this.segundo_apellido = segundo_apellido;
	}
}
