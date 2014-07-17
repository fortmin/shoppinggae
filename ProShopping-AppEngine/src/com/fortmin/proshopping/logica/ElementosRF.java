package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fortmin.proshopping.persistencia.Beacon;
import com.fortmin.proshopping.persistencia.Tag;

public class ElementosRF {

	/*
	 * Insertar un Tag NFC junto con sus datos
	 */
	public void insertTag(EntityManager mgr, String id, String ubicacion,
			String tipo) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Tag tag = mgr.find(Tag.class, id);
		if (tag == null) {
			tag = new Tag(id, ubicacion, tipo);
			mgr.persist(tag);
		}
		trans.commit();
	}

	/*
	 * Actualizar los datos de un Tag NFC ya existente
	 */
	public void updateTag(EntityManager mgr, String id, String ubicacion,
			String tipo) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Tag tag = mgr.find(Tag.class, id);
		if (tag != null) {
			tag.setUbicacion(ubicacion);
			tag.setTipConten(tipo);
			mgr.persist(tag);
		}
		trans.commit();
	}

	/*
	 * Elimina un Tag NFC existente
	 */
	public void deleteTag(EntityManager mgr, String id) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Tag tag = mgr.find(Tag.class, id);
		if (tag != null) {
			mgr.remove(tag);
		}
		trans.commit();
	}

	/*
	 * Insertar un nuevo Beacon
	 */
	public void insertBeacon(EntityManager mgr, String id, String ubicacion,
			String uuid, int major, int minor, int rssi) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Beacon beacon = mgr.find(Beacon.class, id);
		if (beacon == null) {
			beacon = new Beacon(id, ubicacion, uuid, major, minor, rssi);
			mgr.persist(beacon);
		}
		trans.commit();
	}

	/*
	 * Actualizar los datos de un Beacon existente
	 */
	public void updateBeacon(EntityManager mgr, String id, String ubicacion,
			String uuid, int major, int minor, int rssi) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Beacon beacon = mgr.find(Beacon.class, id);
		if (beacon != null) {
			beacon.setUbicacion(ubicacion);
			beacon.setUuid(uuid);
			beacon.setMajor(major);
			beacon.setMinor(minor);
			beacon.setRssi(rssi);
			mgr.persist(beacon);
		}
		trans.commit();
	}

	/*
	 * Elimina un Beacon existente
	 */

	public void deleteBeacon(EntityManager mgr, String id) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Beacon beacon = mgr.find(Beacon.class, id);
		if (beacon != null) {
			mgr.remove(beacon);
		}
		trans.commit();
	}

}
