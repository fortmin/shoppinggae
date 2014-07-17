package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fortmin.proshopping.persistencia.Comercio;

public class Comercios {
	
	/*
	 * Este metodo agrega un comercio al conjunto de comercios del Shopping
	 */
	public void insertComercio(EntityManager mgr, String nomcom, String ubicacion) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Comercio comercio = mgr.find(Comercio.class, nomcom);
		if (comercio == null) {
			comercio = new Comercio(nomcom, ubicacion);
			mgr.persist(comercio);
		}
		trans.commit();
	}

	/*
	 * Este metodo actualiza los datos de un comercio
	 */
	public void updateComercio(EntityManager mgr, String nomcom, String ubicacion) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Comercio comercio = mgr.find(Comercio.class, nomcom);
		if (comercio != null) {
			comercio.setUbicacion(ubicacion);
			mgr.persist(comercio);
		}
		trans.commit();
	}

	/*
	 * Este metodo da de baja un comercio del conjunto de comercios del Shopping
	 */
	public void deleteComercio(EntityManager mgr, String nomcom) {
		Comercio comercio = mgr.find(Comercio.class, nomcom);
		if (comercio != null) {
			mgr.remove(comercio);
		}
	}


}
