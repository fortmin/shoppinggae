package com.fortmin.proshopping.entidades;

import com.google.appengine.api.datastore.Blob;

public class ProductoExtVO {

	private String comercio;
	private String codigo;
	private String nombre;
	private float precio;
	private String detalles;
	private Blob imagen;
	private String tipoImagen;

	public ProductoExtVO(String comercio, String codigo, String nombre,
			float precio, String detalles, Blob imagen, String tipoImg) {
		this.comercio = comercio;
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.detalles = detalles;
		this.imagen = imagen;
		this.setTipoImagen(tipoImg);
	}

	public ProductoExtVO(Producto prod) {
		this.comercio = prod.getComercio();
		this.codigo = prod.getCodigo();
		this.nombre = prod.getNombre();
		this.precio = prod.getPrecio();
		this.detalles = prod.getDetalles();
		this.imagen = prod.getImagen();
		this.setTipoImagen(prod.getTipoImagen());
	}

	public String getComercio() {
		return comercio;
	}

	public void setComercio(String comercio) {
		this.comercio = comercio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getDetalles() {
		return detalles;
	}

	public void setDescripcion(String detalles) {
		this.detalles = detalles;
	}

	public Blob getImagen() {
		if (imagen == null)
			return null;
		else
			return imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

	public String getTipoImagen() {
		return tipoImagen;
	}

	public void setTipoImagen(String tipoImagen) {
		this.tipoImagen = tipoImagen;
	}

}
