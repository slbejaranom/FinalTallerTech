package com.tallertech.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="LLAMADA")
public class Llamada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_LLAMADA", nullable = false)
	long idLlamada;
	
	@Column(name="DURACION", nullable = false)
	long duracion;
	
	@Column(name="TELEFONO_DESTINO", nullable = false)
	String telefonoDestino;
	
	@Column(name="FUE_REPORTADA", nullable = false)
	char fueReportada;
	
	@Column(name="FECHA", nullable = false)
	Date fecha;
	
	@ManyToOne
	@JoinColumn(name="TELEFONO", nullable = false)
	Cliente cliente;

	public Llamada() {
		super();
	}

	public Llamada(long idLlamada, long duracion, String telefonoDestino, char fueReportada, Date fecha,
			Cliente cliente) {
		super();
		this.idLlamada = idLlamada;
		this.duracion = duracion;
		this.telefonoDestino = telefonoDestino;
		this.fueReportada = fueReportada;
		this.fecha = fecha;
		this.cliente = cliente;
	}

	public long getIdLlamada() {
		return idLlamada;
	}

	public void setIdLlamada(long idLlamada) {
		this.idLlamada = idLlamada;
	}

	public long getDuracion() {
		return duracion;
	}

	public void setDuracion(long duracion) {
		this.duracion = duracion;
	}

	public String getTelefonoDestino() {
		return telefonoDestino;
	}

	public void setTelefonoDestino(String telefonoDestino) {
		this.telefonoDestino = telefonoDestino;
	}

	public char getFueReportada() {
		return fueReportada;
	}

	public void setFueReportada(char fueReportada) {
		this.fueReportada = fueReportada;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}