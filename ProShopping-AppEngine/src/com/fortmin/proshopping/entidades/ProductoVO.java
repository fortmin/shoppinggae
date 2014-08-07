package com.fortmin.proshopping.entidades;

public class ProductoVO {
	
	private String comercio;
	private String nombre;
	private float precio;
	
	public ProductoVO(String comercio, String nombre, float precio) {
		this.comercio = comercio;
		this.nombre = nombre;
		this.precio = precio;
	}
	
	public ProductoVO(Producto prod) {
		this.comercio = prod.getComercio();
		this.nombre = prod.getNombre();
		this.precio = prod.getPrecio();
	}

	public String getComercio() {
		return comercio;
	}

	public void setComercio(String comercio) {
		this.comercio = comercio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

}
