package com.fortmin.proshopping.logica;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fortmin.proshopping.entidades.Cliente;
import com.fortmin.proshopping.entidades.Compra;
import com.fortmin.proshopping.entidades.Paquete;
import com.fortmin.proshopping.valueobjects.CarritoVO;
import com.fortmin.proshopping.valueobjects.ComprasVO;
import com.fortmin.proshopping.valueobjects.EstacionamientoVO;
import com.fortmin.proshopping.valueobjects.Mensaje;
import com.fortmin.proshopping.valueobjects.PaqueteVO;

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
				result = new Mensaje("RegistroUsuario", "OK");
			else
				result = new Mensaje("RegistroUsuario", usuario
						+ "::CLIENTE_AUSENTE");
		} else
			result = new Mensaje("RegistroUsuario", usuario
					+ "::USUARIO_EXISTENTE");
		return result;
	}

	/*
	 * Obtener el puntaje actual del cliente
	 */
	public Mensaje getPuntajeCliente(EntityManager mgr, String usuario) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			result = new Mensaje("GetPuntajeCliente", "OK",
					cliente.getPuntaje());
		} else
			result = new Mensaje("GetPuntajeCliente", usuario
					+ "::USUARIO_EXISTENTE");
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
		} else
			result = new Mensaje("GetPuntajeCliente", usuario
					+ "::USUARIO_EXISTENTE");
		return result;
	}

	/*
	 * Establecer el estado de visibilidad del cliente
	 */
	public Mensaje establecerVisibilidadCliente(EntityManager mgr,
			String usuario, boolean visible) {
		Mensaje result = null;
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			cliente.setVisible(visible);
			mgr.persist(cliente);
		} else
			result = new Mensaje("GetPuntajeCliente", usuario
					+ "::USUARIO_EXISTENTE");
		return result;
	}

	/*
	 * Actualizar la posicion del cliente a partir del elemento RF El tipo debe
	 * ser NFCTAG o BEACON
	 */
	public Mensaje actualizarPosicion(EntityManager mgr, String usuario,
			String elemRf, String tipo) {
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
				result = new Mensaje("ActualizarPosicion", "OK");
			} else {
				result = new Mensaje("ActualizarPosicion", usuario
						+ "::USUARIO_INEXISTENTE");
				txn.rollback();
			}
		} else
			result = new Mensaje("ActualizarPosicion", elemRf
					+ "::SIN_UBICACION_DEFINIDA");
		return result;
	}

	/*
	 * Agregar un item al carrito de compras del cliente
	 */
	public Mensaje agregarItemCarrito(EntityManager mgr, String usuario,
			String nompaq) {
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
					result = new Mensaje("AgregarItemCarrito", "OK");
					txn.commit();
				} else {
					result = new Mensaje("AgregarItemCarrito", nompaq + "::"
							+ "PAQUETE_NO_AGREGADO");
					txn.rollback();
				}
			} else {
				result = new Mensaje("AgregarItemCarrito", usuario
						+ "::USUARIO_INEXISTENTE");
				txn.rollback();
			}
		} else
			result = new Mensaje("AgregarItemCarrito", nompaq
					+ "::PAQUETE_INEXISTENTE");
		return result;
	}

	/*
	 * Eliminar un item del carrito de compras del cliente
	 */
	public Mensaje eliminarItemCarrito(EntityManager mgr, String usuario,
			String nompaq) {
		Mensaje result = null;
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			boolean elimino = cliente.eliminarItemCarrito(nompaq);
			mgr.persist(cliente);
			if (elimino)
				result = new Mensaje("EliminarItemCarrito", "OK");
			else
				result = new Mensaje("EliminarItemCarrito", nompaq + "::"
						+ "PAQUETE_NO_ELIMINADO");
		} else
			result = new Mensaje("EliminarItemCarrito", usuario
					+ "::USUARIO_INEXISTENTE");
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

	/*
	 * Checkout del carrito de compras del cliente
	 */
	public Mensaje checkoutCarrito(EntityManager mgr, String usuario) {
		Mensaje result = null;
		CarritoVO carrito = getCarritoCompleto(mgr, usuario);
		if (carrito != null) {
			if (carrito.getCantItems() > 0) {
				EntityTransaction txn = mgr.getTransaction();
				txn.begin();
				Cliente cliente = mgr.find(Cliente.class, usuario);
				if (cliente != null) {
					LinkedList<String> paquetes = carrito.getListaPaquetes();
					Compra compra = new Compra(cliente.getUsuario(),
							carrito.getCantItems(), carrito.getPrecioCarrito(),
							carrito.getPuntosCarrito(), paquetes);
					mgr.persist(compra);
					cliente.vaciarCarrito();
					cliente.setPuntaje(cliente.getPuntaje()
							+ carrito.getPuntosCarrito());
					mgr.persist(cliente);
					txn.commit();
					result = new Mensaje("CheckoutCarrito", "OK");
				} else {
					txn.rollback();
					result = new Mensaje("CheckoutCarrito", usuario
							+ "::USUARIO_INEXISTENTE");
				}
			} else
				result = new Mensaje("CheckoutCarrito", usuario
						+ "::CARRITO_VACIO");
		} else
			result = new Mensaje("CheckoutCarrito", usuario
					+ "::USUARIO_INEXISTENTE");
		return result;
	}

	/*
	 * Lista las compras del cliente
	 */
	public LinkedList<ComprasVO> getCompras(EntityManager mgr, String usuario) {
		LinkedList<ComprasVO> comprasvo = new LinkedList<ComprasVO>();
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			Compras comps = new Compras();
			List<Compra> compras = comps.getComprasCliente(mgr, usuario);
			if (compras != null) {
				ComprasVO compvo;
				Compra comp;
				Iterator<Compra> icompras = compras.iterator();
				while (icompras.hasNext()) {
					comp = icompras.next();
					compvo = new ComprasVO(comp);
					PaqueteVO paquete = null;
					Paquetes paqs = new Paquetes();
					Iterator<String> listpaq = comp.getPaquetes().iterator();
					while (listpaq.hasNext()) {
						String nompaq = listpaq.next();
						paquete = paqs.getPaqueteNombre(mgr, nompaq);
						if (paquete == null)
							paquete = new PaqueteVO(nompaq);
						compvo.agregarPaquete(paquete);
					}
					comprasvo.add(compvo);
				}
				txn.commit();
			} else
				txn.rollback();
		} else
			txn.rollback();
		return comprasvo;
	}

	/*
	 * Agrega puntos al cliente, utilizada para la transferencia de puntos
	 */
	public Mensaje agregarPuntos(EntityManager mgr, String usuario, int puntos) {
		Mensaje result = null;
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			cliente.setPuntaje(cliente.getPuntaje() + puntos);
			mgr.persist(cliente);
			txn.commit();
			result = new Mensaje("AgregarPuntos", "OK");
		} else {
			txn.rollback();
			result = new Mensaje("AgregarPuntos", usuario
					+ "::USUARIO_INEXISTENTE");
		}
		return result;
	}

	/*
	 * Quita puntos al cliente, utilizada para la transferencia de puntos
	 */
	public Mensaje quitarPuntos(EntityManager mgr, String usuario, int puntos) {
		Mensaje result = null;
		EntityTransaction txn = mgr.getTransaction();
		txn.begin();
		Cliente cliente = mgr.find(Cliente.class, usuario);
		if (cliente != null) {
			if (cliente.getPuntaje() > puntos) {
				cliente.setPuntaje(cliente.getPuntaje() - puntos);
				mgr.persist(cliente);
				txn.commit();
				result = new Mensaje("QuitarPuntos", "OK");
			} else {
				txn.rollback();
				result = new Mensaje("QuitarPuntos", usuario
						+ "::SALDO_INSUFICIENTE");
			}
		} else {
			txn.rollback();
			result = new Mensaje("QuitarPuntos", usuario
					+ "::USUARIO_INEXISTENTE");
		}
		return result;
	}

	/*
	 * Devuelve informacion sobre el tiempo de estacionamiento que dispone aun
	 * el cliente para retirarse sin pagar por ello
	 */
	public EstacionamientoVO getTiempoEstacionamiento(EntityManager mgr,
			String usuario) {
		EstacionamientoVO estvo = null;
		Parametros params = new Parametros();
		long pzoest = params.getParametroNumeroValor(mgr,
				"PLAZO_ESTACIONAMIENTO");
		if (pzoest != -1) {
			Cliente cliente = mgr.find(Cliente.class, usuario);
			if (cliente != null) {
				estvo = new EstacionamientoVO(cliente.isPresente());
				if (estvo.isPresente()) {
					estvo.setEntrada(cliente.getUltEntrada());
					estvo.setActual(new Date());
					Utils ut = new Utils();
					estvo.setTopSalidaGratis(ut.sumaFechaSegundos(
							cliente.getUltEntrada(), pzoest));
				}
			}
		}
		return estvo;
	}

	public LinkedList<String> obtenerAmigosANotificar(EntityManager mgr,
			String usuario) {
		LinkedList<String> lamigos = new LinkedList<String>();
		String amigo = null;
		Cliente cliente = null;
		if (usuario.equals("jafortti"))
			amigo = "fminos";
		else if (usuario.equals("fminos"))
			amigo = "jafortti";
		else
			amigo = "jafortti";
		Query querycli = mgr
				.createQuery(
						"SELECT c FROM Cliente c WHERE c.visible = true AND c.usuario = :amigo",
						Cliente.class);
		querycli.setParameter("amigo", amigo);
		try {
			cliente = (Cliente) querycli.getSingleResult();
		} catch (NoResultException e) {
			cliente = null;
		}
		if (cliente != null)
			lamigos.add(cliente.getUsuario());
		return lamigos;
	}

	/*
	 * Notifica a los amigos que estén presentes en el Shopping y que esten
	 * anotados como visibles.
	 */
	public void notificarAmigos(EntityManager mgr, String mensaje,
			String usuario) throws IOException {
		Iterator<String> ilamigos = obtenerAmigosANotificar(mgr, usuario)
				.iterator();
		MessageEndpoint msgendp = new MessageEndpoint();
		while (ilamigos.hasNext()) {
			msgendp.enviarMensaje(mensaje, usuario);
		}
	}

}
