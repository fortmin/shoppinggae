package com.fortmin.proshopping.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Blob;

@Entity
public class Producto {

	@Id
	private String clave;
	private String comercio;
	private String codigo;
	private String nombre;
	private float precio;
	private String detalles;
	private Blob imagen;
	private String tipoImagen;

	public Producto(String comercio, String codigo, String nombre, float precio) {
		this.clave = comercio + "::" + codigo;
		this.comercio = comercio;
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.detalles = "";
		this.imagen = null;
		this.tipoImagen = "";
	}

	public Producto(String comercio, String codigo, String nombre,
			float precio, String detalles) {
		this.clave = comercio + "::" + codigo;
		this.comercio = comercio;
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.detalles = detalles;
		this.imagen = null;
		this.tipoImagen = "";
	}

	public Producto(String comercio, String codigo, String nombre,
			float precio, String detalles, Blob imagen, String tipoImg) {
		this.clave = comercio + "::" + codigo;
		this.comercio = comercio;
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		this.detalles = detalles;
		this.imagen = imagen;
		this.setTipoImagen(tipoImg);
	}

	public Producto(String comercio, String codigo) {
		this.clave = comercio + "::" + codigo;
		this.comercio = comercio;
		this.codigo = codigo;
		this.nombre = null;
		this.precio = 0;
		this.detalles = "";
		this.imagen = null;
		this.setTipoImagen("");
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public String getProducto() {
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

	public void setDetalles(String detalles) {
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

	public String getNombre() {
		return nombre;
	}

	public String getTipoImagen() {
		return tipoImagen;
	}

	public void setTipoImagen(String tipoImagen) {
		this.tipoImagen = tipoImagen;
	}

}
