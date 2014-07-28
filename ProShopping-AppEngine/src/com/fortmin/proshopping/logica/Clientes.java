package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;

import com.fortmin.proshopping.entidades.Cliente;
import com.fortmin.proshopping.entidades.Mensaje;

public class Clientes {
	
	/*
	 * Obtener los datos del paquete en base a su nombre
	 */
	public Cliente getCliente(EntityManager mgr, String usuario) {
		return mgr.find(Cliente.class, usuario);
	}
	
	/*
	 * Averiguar si el cliente esta presente
	 */
	public Mensaje clientePresente(EntityManager mgr, String usuario) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			if (cliente.isPresente())
				result = new Mensaje("RegistroUsuario","OK");
			else
				result = new Mensaje("RegistroUsuario",usuario+"::CLIENTE_AUSENTE");				
		}
		else
			result = new Mensaje("RegistroUsuario",usuario+"::USUARIO_EXISTENTE");
		return result;
	}
	
	/*
	 * Obtener el puntaje actual del cliente
	 */
	public Mensaje getPuntajeCliente(EntityManager mgr, String usuario) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			result = new Mensaje("GetPuntajeCliente", "OK", cliente.getPuntaje());
		}
		else
			result = new Mensaje("GetPuntajeCliente",usuario+"::USUARIO_EXISTENTE");
		return result;
	}

	/*
	 * Obtener el estado de visibilidad del cliente
	 */
	public Mensaje getVisibilidadCliente(EntityManager mgr, String usuario) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			if (cliente.isVisible())
				result = new Mensaje("GetVisibilidadCliente", "VISIBLE");
			else
				result = new Mensaje("GetVisibilidadCliente", "INVISIBLE");
		}
		else
			result = new Mensaje("GetPuntajeCliente",usuario+"::USUARIO_EXISTENTE");
		return result;
	}
	
	/*
	 * Establecer el estado de visibilidad del cliente
	 */
	public Mensaje establecerVisibilidadCliente(EntityManager mgr, String usuario, boolean visible) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			cliente.setVisible(visible);
			mgr.persist(cliente);
		}
		else
			result = new Mensaje("GetPuntajeCliente",usuario+"::USUARIO_EXISTENTE");
		return result;
	}

}
