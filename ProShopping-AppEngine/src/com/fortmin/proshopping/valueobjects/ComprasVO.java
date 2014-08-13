package com.fortmin.proshopping.valueobjects;

import java.util.LinkedList;

import com.fortmin.proshopping.entidades.Compra;

public class ComprasVO {
	
	private String compra;
	private String cliente;
	private boolean entregada;
	private int cantItems;
	private int puntosOtorgados;
	private float precioTotal;
	private LinkedList<PaqueteVO> paquetes;
	
	public ComprasVO(Compra c) {
		this.compra = c.getCompra();
		this.cliente = c.getCliente();
		this.entregada = c.isEntregada();
		this.cantItems = c.getCantItems();
		this.precioTotal = c.getPrecioTotal();
		this.puntosOtorgados = c.getPuntosOtorgados();
		this.paquetes = new LinkedList<PaqueteVO>();
	}
	
	public void agregarPaquete(PaqueteVO paquete) {
		paquetes.addLast(paquete);
		cantItems += 1;
		puntosOtorgados += paquete.getPuntos();
		precioTotal += paquete.getPrecio();
	}

	public String getCompra() {
		return compra;
	}

	public void setCompra(String compra) {
		this.compra = compra;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public boolean isEntregada() {
		return entregada;
	}

	public void setEntregada(boolean entregada) {
		this.entregada = entregada;
	}

	public int getCantItems() {
		return cantItems;
	}

	public void setCantItems(int cantItems) {
		this.cantItems = cantItems;
	}

	public int getPuntosOtorgados() {
		return puntosOtorgados;
	}

	public void setPuntosOtorgados(int puntosO) {
		this.puntosOtorgados = puntosO;
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal(float precioTotal) {
		this.precioTotal = precioTotal;
	}

	public LinkedList<PaqueteVO> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(LinkedList<PaqueteVO> paquetes) {
		this.paquetes = paquetes;
	}


}
