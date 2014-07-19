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
	

}
