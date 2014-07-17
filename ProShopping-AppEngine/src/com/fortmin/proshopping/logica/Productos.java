package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fortmin.proshopping.persistencia.Producto;

public class Productos {

	/*
	 * Este metodo permite agregar un producto a la lista de productos de un
	 * comercio
	 */
	public void insertProducto(EntityManager mgr, String comercio,
			String codigo, String nombre, float precio) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Producto producto = new Producto(comercio, codigo, nombre, precio);
		Producto prodesta = mgr.find(Producto.class, producto.getClave());
		if (prodesta == null) {
			mgr.persist(producto);
		}
		trans.commit();
	}

	/*
	 * Este metodo permite eliminar un producto de la lista de productos de un
	 * comercio
	 */
	public void deleteProducto(EntityManager mgr, String comercio, String codigo) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Producto producto = new Producto(comercio, codigo);
		producto = mgr.find(Producto.class, producto.getClave());
		if (producto != null) {
			mgr.remove(producto);
		}
		trans.commit();
	}

	/*
	 * Este metodo permite agregar un producto a la lista de productos de un
	 * comercio
	 */
	public void updateProducto(EntityManager mgr, String comercio,
			String codigo, String nombre, float precio) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Producto producto = new Producto(comercio, codigo);
		producto = mgr.find(Producto.class, producto.getClave());
		if (producto != null) {
			producto.setNombre(nombre);
			producto.setPrecio(precio);
			mgr.persist(producto);
		}
		trans.commit();
	}

}
