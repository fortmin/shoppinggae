package com.fortmin.proshopping.entidades;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.datanucleus.api.jpa.annotations.Index;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TIPO", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("ACCESO")
public abstract class Acceso {
	
	@Id
	private String nombre;
	private String ubicacion;
	@Index(name="AccElemRfIdx", unique="false")
	private String elementoRF;
	
	public Acceso(String nombre, String ubicacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.elementoRF = null;
	}
	
	public Acceso(String nombre, String ubicacion, String elementoRF) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.elementoRF = elementoRF;
	}
	
	public abstract String getTipo();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getElementoRF() {
		return elementoRF;
	}

	public void setElementoRF(String elementoRF) {
		this.elementoRF = elementoRF;
	}

}
