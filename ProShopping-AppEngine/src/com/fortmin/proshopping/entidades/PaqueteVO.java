package com.fortmin.proshopping.entidades;

import java.util.LinkedList;

public class PaqueteVO {

	private String nombre;
	private int cantProductos;
	private int puntos;
	private float precio;
	private LinkedList<ProductoVO> productos;

	public PaqueteVO(String nombre, int cantProductos, int puntos,
			float precio) {
		this.nombre = nombre;
		this.cantProductos = cantProductos;
		this.puntos = puntos;
		this.precio = precio;
		this.productos = new LinkedList<ProductoVO>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantProductos() {
		return cantProductos;
	}

	public void setCantProductos(int cantProductos) {
		this.cantProductos = cantProductos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public LinkedList<ProductoVO> getProductos() {
		return productos;
	}

	public void setProductos(LinkedList<ProductoVO> productos) {
		this.productos = productos;
	}
	
	public void agregarProducto(Producto producto) {
		ProductoVO prodvo = new ProductoVO(producto.getComercio(), producto.getProducto(), producto.getPrecio());
		this.productos.add(prodvo);
	}

}
