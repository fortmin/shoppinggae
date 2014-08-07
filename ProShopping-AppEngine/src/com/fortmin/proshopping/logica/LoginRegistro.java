package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;

import com.fortmin.proshopping.entidades.Cliente;
import com.fortmin.proshopping.entidades.Mensaje;

public class LoginRegistro {
	
	/* 
	 * Realizar el registro de un nuevo cliente
	 */
	public Mensaje registroUsuario(EntityManager mgr, String usuario, String email, String nombre) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente == null) {
			cliente = new Cliente(usuario, nombre, email);
			// Enviar mail con contrasenia para validar mail
			// por ahora le creamos la contrasenia a mano
			cliente.setClave("mundial");
			mgr.persist(cliente);
			result = new Mensaje("RegistroUsuario","OK");
		}
		else 
			result = new Mensaje("RegistroUsuario",usuario+"::USUARIO_EXISTENTE");
		return result;
	}
	
	/*
	 * Realizar el login del usuario
	 */
	public Mensaje loginUsuario(EntityManager mgr, String usuario, String clave) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			if (cliente.getClave().equals(clave)) {
				cliente.setLogged(true);
				mgr.persist(cliente);
				result = new Mensaje("LoginUsuario","OK");
				}
			else
				result = new Mensaje("LoginUsuario",usuario+"::CLAVE_INCORRECTA");
		}
		else
			result = new Mensaje("LoginUsuario",usuario+"::USUARIO_INEXISTENTE");
		return result;
	}

	/*
	 * Realizar el logoff del usuario
	 */
	public Mensaje logoffUsuario(EntityManager mgr, String usuario, String clave) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			if (cliente.getClave().equals(clave)) {
				if (cliente.isLogged()) {
					cliente.setLogged(false);
					mgr.persist(cliente);
					result = new Mensaje("LogoffUsuario","OK");
				}
				else
					result = new Mensaje("LogoffUsuario",usuario+"::SIN_LOGIN_PREVIO");
			}
			else
				result = new Mensaje("LogoffUsuario",usuario+"::CLAVE_INCORRECTA");
		}
		else
			result = new Mensaje("LogoffUsuario",usuario+"::USUARIO_INEXISTENTE");
		return result;
	}
	
	/*
	 * Es un usuario registrado?
	 */
	public Mensaje usuarioRegistrado(EntityManager mgr, String usuario) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null)
			result = new Mensaje("UsuarioRegistrado","OK");
		else 
			result = new Mensaje("UsuarioRegistrado",usuario+"::USUARIO_INEXISTENTE");
		return result;
	}

}
