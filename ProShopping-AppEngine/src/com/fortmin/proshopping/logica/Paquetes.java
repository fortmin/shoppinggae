package com.fortmin.proshopping.logica;

import java.util.Iterator;
import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.fortmin.proshopping.persistencia.Paquete;
import com.fortmin.proshopping.persistencia.Producto;

public class Paquetes {

	/*
	 * Dada la identificacion de un elemento RF (Tag, Beacon, ...) se devuelve
	 * el paquete promocional asociado al mismo
	 */
	public Paquete getPaqueteRF(EntityManager mgr, String elemRf) {
		Query querypaq = mgr.createQuery(
				"SELECT p FROM Paquete p WHERE p.elementoRF = :elemrf",
				Paquete.class);
		querypaq.setParameter("elemrf", elemRf);
		Paquete paquete = (Paquete) querypaq.getSingleResult();
		return paquete;
	}

	/*
	 * Obtener la lista de productos de un paquete existente
	 */
	public LinkedList<Producto> getProductosPaquete(EntityManager mgr,
			String nompaq) {
		LinkedList<Producto> prodlist = new LinkedList<Producto>();
		Paquete paquete = this.getPaquete(mgr, nompaq);
		if (paquete != null) {
			LinkedList<String> prods = paquete.getProductos();
			Iterator<String> iprods = prods.iterator();
			while (iprods.hasNext()) {
				String claveprod = iprods.next();
				Producto prod = mgr.find(Producto.class, claveprod);
				if (prod != null)
					prodlist.add(prod);
			}
		}
		return prodlist;
	}

	/*
	 * Obtener los datos del paquete en base a su nombre
	 */
	public Paquete getPaquete(EntityManager mgr, String nompaq) {
		return mgr.find(Paquete.class, nompaq);
	}

	/*
	 * Crear un paquete nuevo con una lista de productos vacia y luego los
	 * productos deben agregarse con insertProductoPaquete
	 */
	public void insertPaquete(EntityManager mgr, String nompaq, int puntos,
			float precio, String elemRf) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Paquete paquete = mgr.find(Paquete.class, nompaq);
		if (paquete == null) {
			paquete = new Paquete(nompaq, puntos, precio, elemRf);
			mgr.persist(paquete);
		}
		trans.commit();
	}

	/*
	 * Eliminar un paquete existente de un comercio junto a todos sus datos
	 */
	public void deletePaquete(EntityManager mgr, String nompaq) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Paquete paquete = mgr.find(Paquete.class, nompaq);
		if (paquete != null) {
			mgr.remove(paquete);
		}
		trans.commit();
	}

	/*
	 * Actualizar un paquete existente con nuevos valores de precio y puntos
	 */
	public void updatePaquete(EntityManager mgr, String nompaq, int puntos,
			float precio, String elemRf) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Paquete paquete = mgr.find(Paquete.class, nompaq);
		if (paquete != null) {
			paquete.setPrecio(precio);
			paquete.setPuntos(puntos);
			paquete.setElementoRF(elemRf);
			mgr.persist(paquete);
		}
		trans.commit();
	}

	/*
	 * Insertar un producto en un paquete ya existente
	 */
	public void insertProductoPaquete(EntityManager mgr, String nompaq,
			String comercio, String codigo) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Paquete paquete = mgr.find(Paquete.class, nompaq);
		if (paquete != null) {
			paquete.agregarProducto(comercio, codigo);
			mgr.persist(paquete);
		}
		trans.commit();
	}

	/*
	 * Borrar un producto de un paquete ya existente
	 */
	public void deleteProductoPaquete(EntityManager mgr, String nompaq,
			String comercio, String codigo) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Paquete paquete = mgr.find(Paquete.class, nompaq);
		if (paquete != null) {
			paquete.eliminarProducto(comercio, codigo);
			mgr.persist(paquete);
		}
		trans.commit();
	}

}
