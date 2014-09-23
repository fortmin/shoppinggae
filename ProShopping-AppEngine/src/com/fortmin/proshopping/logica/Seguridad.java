package com.fortmin.proshopping.logica;

import javax.inject.Named;
import javax.persistence.EntityManager;

import com.fortmin.proshopping.valueobjects.Mensaje;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(name = "seguridad", namespace = @ApiNamespace(ownerDomain = "fortmin.com", ownerName = "fortmin.com", packagePath = "proshopping.logica"))
public class Seguridad {

	/*
	 * Realizar el registro de un nuevo cliente
	 */
	@ApiMethod(name = "RegistroUsuario", path = "registro_usuario")
	public Mensaje registroUsuario(@Named("usuario") String usuario,
			@Named("email") String email, @Named("nombre") String nombre) {
		EntityManager mgr = getEntityManager();
		LoginRegistro logreg = new LoginRegistro();
		Mensaje resp = logreg.registroUsuario(mgr, usuario, email, nombre);
		mgr.close();
		return resp;
	}

	/*
	 * Realizar el login del usuario
	 */
	@ApiMethod(name = "LoginUsuario", path = "login_usuario")
	public Mensaje loginUsuario(@Named("usuario") String usuario,
			@Named("password") String clave) {
		EntityManager mgr = getEntityManager();
		LoginRegistro logreg = new LoginRegistro();
		Mensaje resp = logreg.loginUsuario(mgr, usuario, clave);
		mgr.close();
		return resp;
	}

	/*
	 * Realizar el logoff del usuario
	 */
	@ApiMethod(name = "LogoffUsuario", path = "logoff_usuario")
	public Mensaje logoffUsuario(@Named("usuario") String usuario,
			@Named("password") String clave) {
		EntityManager mgr = getEntityManager();
		LoginRegistro logreg = new LoginRegistro();
		Mensaje resp = logreg.loginUsuario(mgr, usuario, clave);
		mgr.close();
		return resp;
	}

	/*
	 * Obtener una referencia al EntityManager
	 */
	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
