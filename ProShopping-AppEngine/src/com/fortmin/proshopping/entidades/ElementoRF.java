package com.fortmin.proshopping.entidades;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ELEMENTORF")
public abstract class ElementoRF {

    @Id
    private String id;
    private String ubicacion;
    private String ultCliente;

    public ElementoRF(String id) {
	this.id = id;
	this.ubicacion = null;
	this.ultCliente = null;
    }

    public ElementoRF(String id, String ubicacion) {
	this.id = id;
	this.ubicacion = ubicacion;
	this.ultCliente = null;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public abstract String getTipo();

    public String getUbicacion() {
	return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
	this.ubicacion = ubicacion;
    }

    public String getUltCliente() {
	return ultCliente;
    }

    public void setUltCliente(String ultCliente) {
	this.ultCliente = ultCliente;
    }

}
