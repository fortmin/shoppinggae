package com.fortmin.proshopping.logica;

import java.util.Iterator;
import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fortmin.proshopping.entidades.Cliente;
import com.fortmin.proshopping.entidades.Mensaje;
import com.fortmin.proshopping.entidades.Paquete;
import com.fortmin.proshopping.entidades.PaqueteVO;
import com.fortmin.proshopping.entidades.CarritoVO;

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

	/*
	 * Actualizar la posicion del cliente a partir del elemento RF
	 * El tipo debe ser NFCTAG o BEACON
	 */
	public Mensaje actualizarPosicion(EntityManager mgr, String usuario, String elemRf, String tipo) {
		Mensaje result = null;
		ElementosRF elems = new ElementosRF();
		String ubicacion = elems.obtenerUbicacionElementoRf(mgr, elemRf, tipo);
		if (ubicacion != null) {
			EntityTransaction txn = mgr.getTransaction();
			txn.begin();
			Cliente cliente = mgr.find(Cliente.class, usuario);
			if (cliente != null) {
				cliente.setUbicacionTemp(ubicacion);
				mgr.persist(cliente);
				txn.commit();
				result = new Mensaje("ActualizarPosicion","OK");
			}
			else {
				result = new Mensaje("ActualizarPosicion",usuario+"::USUARIO_INEXISTENTE");				
				txn.rollback();
			}
		}
		else 
			result = new Mensaje("ActualizarPosicion",elemRf+"::SIN_UBICACION_DEFINIDA");
		return result;
	}
	
	/*
	 * Agregar un item al carrito de compras del cliente
	 */
	public Mensaje agregarItemCarrito(EntityManager mgr, String usuario, String nompaq) {
		Mensaje result = null;
		Paquetes paqs = new Paquetes();
		Paquete paquete = paqs.getPaquete(mgr, nompaq);
		if (paquete != null) {
			EntityTransaction txn = mgr.getTransaction();
			txn.begin();
			Cliente cliente = mgr.find(Cliente.class, usuario);
			if (cliente != null) {
				boolean agrego = cliente.agregarItemCarrito(nompaq);
				if (agrego) {
					mgr.persist(cliente);
					result = new Mensaje("AgregarItemCarrito","OK");
					txn.commit();
				}
				else {
					result = new Mensaje("AgregarItemCarrito",nompaq+"::"+"PAQUETE_NO_AGREGADO");
					txn.rollback();
				}
			}
			else {
				result = new Mensaje("AgregarItemCarrito",usuario+"::USUARIO_INEXISTENTE");
				txn.rollback();
			}
		}
		else
			result = new Mensaje("AgregarItemCarrito",nompaq+"::PAQUETE_INEXISTENTE");
		return result;
	}
	
	/*
	 * Eliminar un item del carrito de compras del cliente
	 */
	public Mensaje eliminarItemCarrito(EntityManager mgr, String usuario, String nompaq) {
		Mensaje result = null;
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			boolean elimino = cliente.eliminarItemCarrito(nompaq);
			mgr.persist(cliente);
			if (elimino)
				result = new Mensaje("EliminarItemCarrito","OK");
			else
				result = new Mensaje("EliminarItemCarrito",nompaq+"::"+"PAQUETE_NO_ELIMINADO");
		}
		else 
			result = new Mensaje("EliminarItemCarrito",usuario+"::USUARIO_INEXISTENTE");
		if (result.getMensaje().equals("OK"))
			txn.commit();
		else
			txn.rollback();
		return result;
	}
	
	/* 
	 * Listar todos los paquetes del carrito
	 */
	public CarritoVO getCarritoCompleto(EntityManager mgr, String usuario) {
		CarritoVO carrito = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			carrito = new CarritoVO(usuario);
			LinkedList<String> listaCarrito = cliente.getCarrito();
			if (listaCarrito.size() > 0) {
				Paquetes paqs = new Paquetes();
				Paquete paquete = null;
				Iterator<String> ilistaCarrito = listaCarrito.iterator();
				while (ilistaCarrito.hasNext()) {
					paquete = paqs.getPaquete(mgr, ilistaCarrito.next());
					if (paquete != null)
						carrito.agregarPaquete(new PaqueteVO(paquete));
				}
			}
		}
		return carrito;
	}
	
}
