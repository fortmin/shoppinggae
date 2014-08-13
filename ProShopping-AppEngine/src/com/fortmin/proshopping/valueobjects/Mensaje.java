package com.fortmin.proshopping.valueobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Mensaje {

	@Id
	@GeneratedValue
	private long clave;
	private String operacion;
	private String mensaje;
	private double valor;

	public Mensaje(String operacion, String mensaje) {
		this.operacion = operacion;
		this.mensaje = mensaje;
	}

	public Mensaje(String operacion, String mensaje, double valor) {
		this.operacion = operacion;
		this.mensaje = mensaje;
		this.valor = valor;
	}

	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
