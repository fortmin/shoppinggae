package com.fortmin.proshopping.logica;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fortmin.proshopping.entidades.Compra;
import com.fortmin.proshopping.valueobjects.Mensaje;

public class Compras {

    /*
     * Devuelve todas las compras del cliente
     */
    @SuppressWarnings("unchecked")
    public List<Compra> getComprasCliente(EntityManager mgr, String usuario) {
	List<Compra> compras = null;
	Query querycom = mgr
		.createQuery(
			"SELECT c FROM Compra c WHERE c.cliente = :usuario ORDER BY c.compra DESC",
			Compra.class);
	querycom.setParameter("usuario", usuario);
	try {
	    compras = (List<Compra>) querycom.getResultList();
	} catch (NoResultException e) {
	    compras = null;
	}
	return compras;
    }

    /*
     * Marca una compra como entregada al cliente
     */
    public Mensaje marcarCompraEntregada(EntityManager mgr, String compmar) {
	Mensaje result = null;
	Compra compra = mgr.find(Compra.class, compmar);
	if (compra != null) {
	    compra.setEntregada(true);
	    mgr.persist(compra);
	} else
	    result = new Mensaje("MarcarCompraEntregada", compmar
		    + "::COMPRA_INEXISTENTE");

	return result;
    }

    /*
     * Ver la lista completa de compras pendiente de entrega
     */
    @SuppressWarnings("unchecked")
    public List<Compra> getComprasPendienteEntrega(EntityManager mgr) {
	List<Compra> compras = null;
	Query querycom = mgr
		.createQuery(
			"SELECT c FROM Compra c WHERE c.entregada = :entreg ORDER BY c.compra DESC",
			Compra.class);
	querycom.setParameter("entreg", false);
	try {
	    compras = (List<Compra>) querycom.getResultList();
	} catch (NoResultException e) {
	    compras = null;
	}
	return compras;
    }

}
