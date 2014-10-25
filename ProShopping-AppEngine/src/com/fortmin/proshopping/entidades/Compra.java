package com.fortmin.proshopping.entidades;

import java.util.Date;
import java.util.LinkedList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

import org.datanucleus.api.jpa.annotations.Index;

import com.fortmin.proshopping.logica.Utils;

@Entity
public class Compra {

    @Id
    private String compra;
    @Index(name = "CompCliIdx", unique = "false")
    private String cliente;
    @Index(name = "CompEntIdx", unique = "false")
    private boolean entregada;
    private int cantItems;
    private float precioTotal;
    private int puntosOtorgados;
    @ElementCollection(fetch = FetchType.EAGER)
    private LinkedList<String> paquetes;

    public Compra(String cliente, int cantItems, float precio, int puntOtor,
	    LinkedList<String> paquetes) {
	Date fecha = new Date();
	Utils utils = new Utils();
	String dia = utils.obtenerFechaString(fecha, "yyyyMMdd");
	String hora = utils.obtenerHoraString(fecha, "hhmmss");
	this.compra = dia + "-" + hora + "-" + cliente;
	this.cliente = cliente;
	this.entregada = false;
	this.cantItems = cantItems;
	this.precioTotal = precio;
	this.puntosOtorgados = puntOtor;
	this.paquetes = paquetes;
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

    public float getPrecioTotal() {
	return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
	this.precioTotal = precioTotal;
    }

    public int getPuntosOtorgados() {
	return puntosOtorgados;
    }

    public void setPuntosOtorgados(int puntosOtorgados) {
	this.puntosOtorgados = puntosOtorgados;
    }

    public LinkedList<String> getPaquetes() {
	return paquetes;
    }

    public void setPaquetes(LinkedList<String> paquetes) {
	this.paquetes = paquetes;
    }

}
