package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fortmin.proshopping.entidades.Ubicacion;

public class Ubicaciones {

	/*
	 * Este metodo permite agregar una ubicacion al conjunto de ubicaciones del
	 * Shopping
	 */
	public void insertUbicacion(EntityManager mgr, String nombre,
			String edificio, int piso, String sector, String area, String lugar) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Ubicacion ubicacion = mgr.find(Ubicacion.class, nombre);
		if (ubicacion == null) {
			ubicacion = new Ubicacion(nombre, edificio, piso, sector, area,
					lugar);
			mgr.persist(ubicacion);
		}
		trans.commit();
	}

	/*
	 * Este metodo permite actualizar una ubicacion del conjunto de ubicaciones
	 * del Shopping
	 */
	public void updateUbicacion(EntityManager mgr, String nombre,
			String edificio, int piso, String sector, String area, String lugar) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Ubicacion ubicacion = mgr.find(Ubicacion.class, nombre);
		if (ubicacion != null) {
			ubicacion.setEdificio(edificio);
			ubicacion.setPiso(piso);
			ubicacion.setSector(sector);
			ubicacion.setArea(area);
			ubicacion.setLugar(lugar);
			mgr.persist(ubicacion);
		}
		trans.commit();
	}

	/*
	 * Este metodo permite eliminar una ubicacion del conjunto de ubicaciones
	 * del Shopping
	 */
	public void deleteUbicacion(EntityManager mgr, String nomubi) {
		Ubicacion ubicacion = mgr.find(Ubicacion.class, nomubi);
		if (ubicacion != null)
			mgr.remove(ubicacion);
	}

	/*
	 * Este metodo permite obtener los datos de una ubicacion del conjunto de
	 * ubicaciones del Shopping
	 */
	public Ubicacion getUbicacion(EntityManager mgr, String nombre) {
		Ubicacion ubic = null;
		Ubicacion ubicacion = mgr.find(Ubicacion.class, nombre);
		if (nombre != null) {
			ubic = ubicacion;
		}
		return ubic;
	}
	
}
