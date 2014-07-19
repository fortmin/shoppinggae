package com.fortmin.proshopping.entidades;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name="TIPO", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("PEATONAL")
public class Peatonal extends Acceso {

	public Peatonal(String nombre, String ubicacion) {
		super(nombre, ubicacion);
	}
	
	public Peatonal(String nombre, String ubicacion, String elementoRF) {
		super(nombre, ubicacion, elementoRF);
	}

	@Override
	public String getTipo() {
		return "PEATONAL";
	}

}
