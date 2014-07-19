package com.fortmin.proshopping.logica;

import javax.inject.Named;
import javax.persistence.EntityManager;

import com.fortmin.proshopping.entidades.ConfigLong;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(name = "administracion", namespace = @ApiNamespace(ownerDomain = "fortmin.com", ownerName = "fortmin.com", packagePath = "proshopping.logica"))
public class Administracion {
	
	/*
	 * Este metodo permite agregar un parametro numerico al conjunto
	 * de parametros del sistema
	 */
	@ApiMethod(name = "insertParametroNumero", path = "insert_parametro_numero")
	public void insertParametroNumero(@Named("parametro") String nomparam,
			@Named("valor") long valor) {
		EntityManager mgr = getEntityManager();
		Parametros params = new Parametros();
		params.insertParametroNumero(mgr, nomparam, valor);
		mgr.close();
	}

	/*
	 * Este metodo permite actualizar el valor de un parametro numerico del conjunto
	 * de parametros del sistema
	 */
	@ApiMethod(name = "updateParametroNumero", path = "update_parametro_numero")
	public void updateParametroNumero(@Named("parametro") String nomparam,
			@Named("valor") long valor) {
		EntityManager mgr = getEntityManager();
		Parametros params = new Parametros();
		params.updateParametroNumero(mgr, nomparam, valor);
		mgr.close();
	}

	/*
	 * Este metodo permite eliminar un parametro numerico del conjunto
	 * de parametros del sistema
	 */
	@ApiMethod(name = "deleteParametroNumero", path = "delete_parametro_numero")
	public void deleteParametroNumero(@Named("parametro") String nomparam) {
		EntityManager mgr = getEntityManager();
		Parametros params = new Parametros();
		params.deleteParametroNumero(mgr, nomparam);
		mgr.close();
	}

	/*
	 * Este metodo permite obtener el valor de un parametro numerico 
	 * del conjunto de parametros del sistema
	 */
	@ApiMethod(name = "getParametroNumero", path = "get_parametro_numero")
	public ConfigLong getParametroNumero(@Named("parametro") String nomparam) {
		EntityManager mgr = getEntityManager();
		Parametros params = new Parametros();
		ConfigLong valor = params.getParametroNumero(mgr, nomparam);
		mgr.close();
		return valor;
	}

	/*
	 * Obtener una referencia al EntityManager
	 */
	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
