package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fortmin.proshopping.entidades.DeviceInfo;

public class Dispositivos {

	/*
	 * Dado el nombre de usuario devuelve la informacion asociada al movil
	 * incluyendo el codigo de registro GCM
	 */
	public DeviceInfo getDeviceInfoPorUsuario(EntityManager mgr, String usuario) {
		DeviceInfo movilInfo = null;
		Query querydev = mgr
				.createQuery(
						"SELECT d FROM DeviceInfo d WHERE d.deviceInformation = :usuario",
						DeviceInfo.class);
		querydev.setParameter("usuario", usuario);
		try {
			movilInfo = (DeviceInfo) querydev.getSingleResult();
		} catch (NoResultException e) {
			movilInfo = null;
		}
		return movilInfo;
	}

}
