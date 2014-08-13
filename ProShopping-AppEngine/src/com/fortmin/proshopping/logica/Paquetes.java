package com.fortmin.proshopping.logica;

import java.util.Iterator;
import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fortmin.proshopping.entidades.Paquete;
import com.fortmin.proshopping.entidades.Producto;
import com.fortmin.proshopping.valueobjects.PaqueteVO;

public class Paquetes {

	/*
	 * Devuelve el paquete completo con el nombre dado
	 */
	public PaqueteVO getPaqueteNombre(EntityManager mgr, String nompaq) {
		PaqueteVO paquete = null;
		Paquete paq = this.getPaquete(mgr, nompaq);
		if (paq != null) {
			LinkedList<Producto> prods = this.getProductosPaquete(mgr, paq.getNombre());
			if (prods.size() > 0) {
				paquete = new PaqueteVO(paq.getNombre(), paq.getCantProductos(), paq.getPuntos(), paq.getPrecio());
				Iterator<Producto> iprods = prods.iterator();
				while (iprods.hasNext()) {
					paquete.agregarProducto(iprods.next());
				}
			}
		}
		return paquete;
	}

	/*
	 * Dada la identificacion de un elemento RF (Tag, Beacon, ...) se devuelve
	 * el paquete promocional asociado al mismo
	 */
	public PaqueteVO getPaqueteCompleto(EntityManager mgr, String elemRf) {
		PaqueteVO paquete = null;
		Paquete paq = this.getPaqueteRF(mgr, elemRf);
		if (paq != null) {
			LinkedList<Producto> prods = this.getProductosPaquete(mgr, paq.getNombre());
			if (prods.size() > 0) {
				paquete = new PaqueteVO(paq.getNombre(), paq.getCantProductos(), paq.getPuntos(), paq.getPrecio());
				Iterator<Producto> iprods = prods.iterator();
				while (iprods.hasNext()) {
					paquete.agregarProducto(iprods.next());
				}
			}
		}
		return paquete;
	}
	
	/*
	 * Dada la identificacion de un elemento RF (Tag, Beacon, ...) se devuelve
	 * el paquete promocional asociado al mismo
	 */
	public Paquete getPaqueteRF(EntityManager mgr, String elemRf) {
		Paquete paquete = null;
		Query querypaq = mgr.createQuery(
				"SELECT p FROM Paquete p WHERE p.elementoRF = :elemrf",
				Paquete.class);
		querypaq.setParameter("elemrf", elemRf);
		try {
			paquete = (Paquete) querypaq.getSingleResult();
		} catch (NoResultException e) {
			paquete = null;
		}
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
