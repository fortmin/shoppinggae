package com.fortmin.proshopping.entidades;

import java.util.Date;
import java.util.LinkedList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

@Entity
public class Cliente {

	@Id
	private String usuario;
	private String clave;
	private String nombre;
	private String email;
	private int puntaje;
	private boolean logged;
	private boolean presente;
	private boolean visible;
	private Date ultEntrada;
	private Date ultSalida;
	private String ubicacionTemp;
	@ElementCollection(fetch = FetchType.EAGER)
	private LinkedList<String> carrito;

	public Cliente(String usuario, String nombre, String email) {
		this.usuario = usuario;
		this.clave = null;
		this.nombre = nombre;
		this.email = email;
		this.puntaje = 0;
		this.logged = false;
		this.presente = false;
		this.visible = false;
		this.ultEntrada = new Date(0);
		this.ultSalida = new Date(0);
		this.ubicacionTemp = null;
		this.carrito = new LinkedList<String>();
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public boolean isPresente() {
		return presente;
	}

	public void setPresente(boolean presente) {
		this.presente = presente;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Date getUltEntrada() {
		return ultEntrada;
	}

	public void setUltEntrada(Date ultEntrada) {
		this.ultEntrada = ultEntrada;
	}

	public Date getUltSalida() {
		return ultSalida;
	}

	public void setUltSalida(Date ultSalida) {
		this.ultSalida = ultSalida;
	}

	public String getUbicacionTemp() {
		return ubicacionTemp;
	}

	public void setUbicacionTemp(String ubicacionTemp) {
		this.ubicacionTemp = ubicacionTemp;
	}

	public LinkedList<String> getCarrito() {
		return carrito;
	}

	public void setCarrito(LinkedList<String> carrito) {
		this.carrito = carrito;
	}

	public boolean agregarItemCarrito(String nomPaquete) {
		if (!hasItemCarrito(nomPaquete)) {
			carrito.add(nomPaquete);
			return true;
		}
		return false;
	}

	public boolean eliminarItemCarrito(String nomPaquete) {
		boolean salir = false;
		for (int i = 0; i < carrito.size() && !salir; i++) {
			if (carrito.get(i).equals(nomPaquete)) {
				carrito.remove(i);
				salir = true;
			}
		}
		return salir;
	}

	public boolean hasItemCarrito(String nomPaquete) {
		boolean esta = false;
		for (int i = 0; i < carrito.size() && !esta; i++) {
			if (carrito.get(i).equals(nomPaquete)) {
				esta = true;
			}
		}
		return esta;
	}

	public void vaciarCarrito() {
		this.carrito = new LinkedList<String>();
	}
}
