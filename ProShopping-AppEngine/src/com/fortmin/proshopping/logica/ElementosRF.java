package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fortmin.proshopping.entidades.Beacon;
import com.fortmin.proshopping.entidades.Tag;
import com.fortmin.proshopping.valueobjects.Mensaje;

public class ElementosRF {

    /*
     * Devuelve el nombre de la ubicacion asociada a un elementoRf El tipo debe
     * ser NFCTAG o BEACON
     */
    public String obtenerUbicacionElementoRf(EntityManager mgr, String id,
	    String tipo) {
	String ubicacion = null;
	if (tipo.equals("NFCTAG")) {
	    Tag tag = mgr.find(Tag.class, id);
	    if (tag != null)
		ubicacion = tag.getUbicacion();
	} else if (tipo.equals("BEACON")) {
	    Beacon beacon = mgr.find(Beacon.class, id);
	    if (beacon != null)
		ubicacion = beacon.getUbicacion();
	}
	return ubicacion;
    }

    /*
     * Insertar un Tag NFC junto con sus datos
     */
    public void insertTag(EntityManager mgr, String id, String ubicacion,
	    String tipo) {
	Tag tag = mgr.find(Tag.class, id);
	if (tag == null) {
	    tag = new Tag(id, ubicacion, tipo);
	    mgr.persist(tag);
	}
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
	Tag tag = mgr.find(Tag.class, id);
	if (tag != null) {
	    mgr.remove(tag);
	}
    }

    /*
     * Insertar un nuevo Beacon
     */
    public void insertBeacon(EntityManager mgr, String id, String ubicacion,
	    String uuid, int major, int minor, int rssi) {
	Beacon beacon = mgr.find(Beacon.class, id);
	if (beacon == null) {
	    beacon = new Beacon(id, ubicacion, uuid, major, minor, rssi);
	    mgr.persist(beacon);
	}
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
	Beacon beacon = mgr.find(Beacon.class, id);
	if (beacon != null) {
	    mgr.remove(beacon);
	}
    }

    /*
     * Calibrar un Beacon
     */
    public void calibrarBeacon(EntityManager mgr, String id, int calibre) {
	EntityTransaction trans = mgr.getTransaction();
	trans.begin();
	Beacon beacon = mgr.find(Beacon.class, id);
	if (beacon != null) {
	    beacon.setCalibre(calibre);
	    beacon.setCalibrado(true);
	    mgr.persist(beacon);
	}
	trans.commit();
    }

    /*
     * Obtener calibrado, retorna -9999 si no esta calibrado
     */
    public Mensaje getCalibradoBeacon(EntityManager mgr, String id) {
	Mensaje resp = null;
	Beacon beacon = mgr.find(Beacon.class, id);
	if (beacon != null) {
	    if (beacon.isCalibrado()) {
		int calibre = beacon.getCalibre();
		resp = new Mensaje("GetCalibradoBeacon", "OK", calibre);
	    } else
		resp = new Mensaje("GetCalibradoBeacon", id
			+ "::BEACON_NO_CALIBRADO");
	} else
	    resp = new Mensaje("GetCalibradoBeacon", id
		    + "::BEACON_INEXISTENTE");
	return resp;
    }

    /*
     * Establecer el cliente que hay cerca de un Tag o Beacon
     */
    public Mensaje establecerClienteCerca(EntityManager mgr, String elemRf,
	    String tipo, String usuario) {
	Mensaje resp = null;
	EntityTransaction trans = mgr.getTransaction();
	trans.begin();
	if (tipo.equals("BEACON")) {
	    Beacon beacon = mgr.find(Beacon.class, elemRf);
	    if (beacon != null) {
		beacon.setUltCliente(usuario);
		mgr.persist(beacon);
		trans.commit();
	    } else {
		resp = new Mensaje("EstablecerClienteCerca", elemRf
			+ "::BEACON_INEXISTENTE");
		trans.rollback();
	    }
	} else if (tipo.equals("NFCTAG")) {
	    Tag tag = mgr.find(Tag.class, elemRf);
	    if (tag != null) {
		tag.setUltCliente(usuario);
		mgr.persist(tag);
		trans.commit();
	    } else {
		resp = new Mensaje("EstablecerClienteCerca", elemRf
			+ "::TAG_INEXISTENTE");
		trans.rollback();
	    }
	} else {
	    resp = new Mensaje("EstablecerClienteCerca", elemRf
		    + "::TAG_INEXISTENTE");
	    trans.rollback();
	}
	return resp;
    }

    /*
     * Obtener el usuario del cliente que esta cerca de un Tag o Beacon
     */
    public Mensaje getClienteCerca(EntityManager mgr, String elemRf, String tipo) {
	Mensaje resp = null;
	EntityTransaction trans = mgr.getTransaction();
	trans.begin();
	if (tipo.equals("BEACON")) {
	    Beacon beacon = mgr.find(Beacon.class, elemRf);
	    if (beacon != null) {
		String cliente = beacon.getUltCliente();
		if (cliente == null)
		    resp = new Mensaje("GetClienteCerca", elemRf
			    + "::SIN_CLIENTE");
		else {
		    beacon.setUltCliente(null);
		    mgr.persist(beacon);
		    resp = new Mensaje("GetClienteCerca", "OK", cliente);
		}
		trans.commit();
	    } else {
		resp = new Mensaje("GetClienteCerca", elemRf
			+ "::BEACON_INEXISTENTE");
		trans.rollback();
	    }
	} else if (tipo.equals("NFCTAG")) {
	    Tag tag = mgr.find(Tag.class, elemRf);
	    if (tag != null) {
		String cliente = tag.getUltCliente();
		if (cliente == null)
		    resp = new Mensaje("GetClienteCerca", elemRf
			    + "::SIN_CLIENTE");
		else {
		    tag.setUltCliente(null);
		    mgr.persist(tag);
		    resp = new Mensaje("GetClienteCerca", "OK", cliente);
		}
		trans.commit();
	    } else {
		resp = new Mensaje("GetClienteCerca", elemRf
			+ "::TAG_INEXISTENTE");
		trans.rollback();
	    }
	}
	return resp;
    }

}
