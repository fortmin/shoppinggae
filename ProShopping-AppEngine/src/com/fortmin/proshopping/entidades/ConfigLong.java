package com.fortmin.proshopping.entidades;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name="TIPO", discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("CONFIGLONG")
public class ConfigLong extends Config {
	
	private long valor;

	public ConfigLong(String parametro, long valor) {
		super(parametro);
		this.setValor(valor);
	}

	@Override
	public String getTipo() {
		return "LONG";
	}

	public long getValor() {
		return valor;
	}

	public void setValor(long valor) {
		this.valor = valor;
	}

}
