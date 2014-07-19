package com.fortmin.proshopping.entidades;

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

	public Mensaje(String operacion, String mensaje) {
		this.operacion = operacion;
		this.mensaje = mensaje;
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

}
