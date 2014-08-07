package com.fortmin.proshopping.entidades;

import java.util.LinkedList;

public class CarritoVO {

	private String cliente;
	private int cantItems;
	private int puntosCarrito;
	private float precioCarrito;
	private LinkedList<PaqueteVO> paquetes;
	
	public CarritoVO(String cliente) {
		this.cliente = cliente;
		this.cantItems = 0;
		this.precioCarrito = 0;
		this.puntosCarrito = 0;
		this.paquetes = new LinkedList<PaqueteVO>();
	}
	
	public void agregarPaquete(PaqueteVO paquete) {
		paquetes.addLast(paquete);
		cantItems += 1;
		puntosCarrito += paquete.getPuntos();
		precioCarrito += paquete.getPrecio();
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public int getCantItems() {
		return cantItems;
	}

	public void setCantItems(int cantItems) {
		this.cantItems = cantItems;
	}

	public int getPuntosCarrito() {
		return puntosCarrito;
	}

	public void setPuntosCarrito(int puntosCarrito) {
		this.puntosCarrito = puntosCarrito;
	}

	public float getPrecioCarrito() {
		return precioCarrito;
	}

	public void setPrecioCarrito(float precioCarrito) {
		this.precioCarrito = precioCarrito;
	}

	public LinkedList<PaqueteVO> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(LinkedList<PaqueteVO> paquetes) {
		this.paquetes = paquetes;
	}

}
