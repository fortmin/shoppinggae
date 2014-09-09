package com.fortmin.proshopping.valueobjects;

import java.util.Date;

public class EstacionamientoVO {

	private boolean presente;
	private Date entrada;
	private Date actual;
	private Date topSalidaGratis;

	public EstacionamientoVO() {
		this.presente = false;
		this.entrada = new Date(0);
		this.actual = new Date();
		this.topSalidaGratis = new Date(0);
	}

	public EstacionamientoVO(boolean presente) {
		this.presente = presente;
		this.entrada = new Date(0);
		this.actual = new Date();
		this.topSalidaGratis = new Date(0);
	}

	public boolean isPresente() {
		return presente;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getActual() {
		return actual;
	}

	public void setActual(Date actual) {
		this.actual = actual;
	}

	public Date getTopSalidaGratis() {
		return topSalidaGratis;
	}

	public void setTopSalidaGratis(Date topSalidaGratis) {
		this.topSalidaGratis = topSalidaGratis;
	}

}
