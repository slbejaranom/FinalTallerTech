package com.tallertech.app.entity;

public class Status {
	
	int llamadasHechas;
	int llamadasReportadas;
	int clientesRegistrados;
	
	public Status() {
		super();
	}

	public Status(int llamadasHechas, int llamadasReportadas, int clientesRegistrados) {
		super();
		this.llamadasHechas = llamadasHechas;
		this.llamadasReportadas = llamadasReportadas;
		this.clientesRegistrados = clientesRegistrados;
	}

	public int getLlamadasHechas() {
		return llamadasHechas;
	}

	public void setLlamadasHechas(int llamadasHechas) {
		this.llamadasHechas = llamadasHechas;
	}

	public int getLlamadasReportadas() {
		return llamadasReportadas;
	}

	public void setLlamadasReportadas(int llamadasReportadas) {
		this.llamadasReportadas = llamadasReportadas;
	}

	public int getClientesRegistrados() {
		return clientesRegistrados;
	}

	public void setClientesRegistrados(int clientesRegistrados) {
		this.clientesRegistrados = clientesRegistrados;
	}
}
