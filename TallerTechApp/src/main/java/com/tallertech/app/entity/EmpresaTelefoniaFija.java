package com.tallertech.app.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="EMPRESA_TELEFONIA_FIJA")
public class EmpresaTelefoniaFija {
	
	@Id	
	@Column(name="NIT", nullable = false)
	String nit;
	
	@Column(name="RAZON_SOCIAL", nullable = false)
	String razon_social;
	
	@Column(name="COSTO_SEGUNDO", nullable = false)
	float costo_segundo;
	
	@Column(name="FECHA_CREACION", nullable = false)
	Date fecha_creacion;
	
	@Column(name="ESTA_ACTIVO", nullable = false)
	char esta_activo;
	
	@Column(name="PERIODICIDAD_ARCHIVO", nullable = false)
	int periodicidad_archivo;
	
	@Column(name="PRIMER_PARAMETRO_ARCHIVO", nullable = false)
	int primer_param_archivo;
	
	@Column(name="SEGUNDO_PARAMETRO_ARCHIVO", nullable = false)
	int segundo_param_archivo;
	
	@Column(name="TERCER_PARAMETRO_ARCHIVO", nullable = false)
	int tercer_param_archivo;
	
	@Column(name="CUARTO_PARAMETRO_ARCHIVO", nullable = false)
	int cuarto_param_archivo;
	
	@Column(name="QUINTO_PARAMETRO_ARCHIVO", nullable = false)
	int quinto_param_archivo;

	@JsonIgnore
	@OneToMany(targetEntity=ClienteEmpresa.class,cascade = CascadeType.ALL, mappedBy="empresaTelefoniaFija")
	Set<ClienteEmpresa> convenios;
	
	
	public EmpresaTelefoniaFija() {
		super();
	}
	
	public EmpresaTelefoniaFija(String nit, String razon_social, Date fecha_creacion, char esta_activo,
			int periodicidad_archivo, int primer_param_archivo, int segundo_param_archivo, int tercer_param_archivo,
			int cuarto_param_archivo, int quinto_param_archivo, float costo_segundo) {
		super();
		this.nit = nit;
		this.razon_social = razon_social;
		this.fecha_creacion = fecha_creacion;
		this.esta_activo = esta_activo;
		this.periodicidad_archivo = periodicidad_archivo;
		this.primer_param_archivo = primer_param_archivo;
		this.segundo_param_archivo = segundo_param_archivo;
		this.tercer_param_archivo = tercer_param_archivo;
		this.cuarto_param_archivo = cuarto_param_archivo;
		this.quinto_param_archivo = quinto_param_archivo;
		this.costo_segundo = costo_segundo;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getRazon_social() {
		return razon_social;
	}

	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public char getEsta_activo() {
		return esta_activo;
	}

	public void setEsta_activo(char esta_activo) {
		this.esta_activo = esta_activo;
	}

	public int getPeriodicidad_archivo() {
		return periodicidad_archivo;
	}

	public void setPeriodicidad_archivo(int periodicidad_archivo) {
		this.periodicidad_archivo = periodicidad_archivo;
	}

	public int getPrimer_param_archivo() {
		return primer_param_archivo;
	}

	public void setPrimer_param_archivo(int primer_param_archivo) {
		this.primer_param_archivo = primer_param_archivo;
	}

	public int getSegundo_param_archivo() {
		return segundo_param_archivo;
	}

	public void setSegundo_param_archivo(int segundo_param_archivo) {
		this.segundo_param_archivo = segundo_param_archivo;
	}

	public int getTercer_param_archivo() {
		return tercer_param_archivo;
	}

	public void setTercer_param_archivo(int tercer_param_archivo) {
		this.tercer_param_archivo = tercer_param_archivo;
	}

	public int getCuarto_param_archivo() {
		return cuarto_param_archivo;
	}

	public void setCuarto_param_archivo(int cuarto_param_archivo) {
		this.cuarto_param_archivo = cuarto_param_archivo;
	}

	public int getQuinto_param_archivo() {
		return quinto_param_archivo;
	}

	public void setQuinto_param_archivo(int quinto_param_archivo) {
		this.quinto_param_archivo = quinto_param_archivo;
	}

	public Set<ClienteEmpresa> getConvenios() {
		return convenios;
	}

	public void setConvenios(Set<ClienteEmpresa> convenios) {
		this.convenios = convenios;
	}
	

	public float getCosto_segundo() {
		return costo_segundo;
	}

	public void setCosto_segundo(float costo_segundo) {
		this.costo_segundo = costo_segundo;
	}
}
