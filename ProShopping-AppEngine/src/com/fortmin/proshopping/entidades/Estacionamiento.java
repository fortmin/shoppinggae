package com.fortmin.proshopping.entidades;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name="TIPO", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("ESTACIONAMIENTO")
public class Estacionamiento extends Acceso {
	
	private boolean controlBarrera;
	private boolean barreraHabilitada;

	public Estacionamiento(String nombre, String ubicacion, boolean controlBarrera) {
		super(nombre, ubicacion);
		this.controlBarrera = controlBarrera;
		this.barreraHabilitada = false;
	}

	public Estacionamiento(String nombre, String ubicacion, String elementoRF, boolean controlBarrera) {
		super(nombre, ubicacion, elementoRF);
		this.controlBarrera = controlBarrera;
		this.barreraHabilitada = false;
	}

	@Override
	public String getTipo() {
		return "ESTACIONAMIENTO";
	}

	public boolean isControlBarrera() {
		return controlBarrera;
	}

	public void setControlBarrera(boolean controlBarrera) {
		this.controlBarrera = controlBarrera;
	}

	public boolean isBarreraHabilitada() {
		return barreraHabilitada;
	}

	public void setBarreraHabilitada(boolean barreraHabilitada) {
		this.barreraHabilitada = barreraHabilitada;
	}

}
