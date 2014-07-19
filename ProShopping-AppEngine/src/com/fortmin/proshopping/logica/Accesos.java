package com.fortmin.proshopping.logica;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.fortmin.proshopping.entidades.Acceso;
import com.fortmin.proshopping.entidades.Cliente;
import com.fortmin.proshopping.entidades.Estacionamiento;
import com.fortmin.proshopping.entidades.Mensaje;

public class Accesos {

	/*
	 * Ingreso al Shopping a través de un estacionamiento
	 */
	public Mensaje ingresoEstacionamiento(EntityManager mgr, String elemRf,
			String usuario) {
		Mensaje result = null;
		Acceso acceso = this.getTipoAcceso(mgr, elemRf);
		if (acceso != null) {
			if (acceso.getTipo().equals("ESTACIONAMIENTO")) {
				Clientes clis = new Clientes();
				Cliente cliente = clis.getCliente(mgr, usuario);
				if (cliente != null) {
					cliente.setUltEntrada(new Date());
					cliente.setPresente(true);
					mgr.persist(cliente);
					result = new Mensaje("IngresoEstacionamiento", "OK");
				} else
					result = new Mensaje("IngresoEstacionamiento", usuario
							+ "::CLIENTE_INEXISTENTE");
			} else
				result = new Mensaje("IngresoEstacionamiento", elemRf + "::"
						+ acceso.getNombre() + "::NO_ES_ACCESO_ESTACIONAMIENTO");
		} else
			result = new Mensaje("IngresoEstacionamiento", elemRf
					+ "::SIN_ACCESO_RELACIONADO");
		return result;
	}

	/*
	 * Ingreso al Shopping a través de un estacionamiento
	 */
	public Mensaje egresoEstacionamiento(EntityManager mgr, String elemRf,
			String usuario) {
		Mensaje result = null;
		Acceso acceso = this.getTipoAcceso(mgr, elemRf);
		if (acceso != null) {
			if (acceso.getTipo().equals("ESTACIONAMIENTO")) {
				Clientes clis = new Clientes();
				Cliente cliente = clis.getCliente(mgr, usuario);
				if (cliente != null) {
					cliente.setUltSalida(new Date());
					cliente.setPresente(false);
					mgr.persist(cliente);
					Utils utils = new Utils();
					Parametros params = new Parametros();
					if (utils.diffFechasSegundos(cliente.getUltEntrada(),cliente.getUltSalida()) < 
							params.getParametroNumero(mgr, "PLAZO_ESTACIONAMIENTO").getValor())
						result = new Mensaje("EgresoEstacionamiento", "OK");
					else
						result = new Mensaje("EgresoEstacionamiento", usuario
								+ "::PLAZO_VENCIDO");
				} else
					result = new Mensaje("EgresoEstacionamiento", usuario
							+ "::CLIENTE_INEXISTENTE");
			} else
				result = new Mensaje("EgresoEstacionamiento", elemRf + "::"
						+ acceso.getNombre() + "::NO_ES_ACCESO_ESTACIONAMIENTO");
		} else
			result = new Mensaje("EgresoEstacionamiento", elemRf
					+ "::SIN_ACCESO_RELACIONADO");
		return result;
	}

	/*
	 * Este metodo permite agregar un nuevo acceso de tipo estacionamiento a la
	 * lista de accesos del Shopping
	 */
	public void insertAccesoEstacionamiento(EntityManager mgr, String nombre,
			String ubicacion, String elementoRf, boolean barrera) {
		Estacionamiento est = new Estacionamiento(nombre, ubicacion,
				elementoRf, barrera);
		Estacionamiento estaesta = mgr.find(Estacionamiento.class, nombre);
		if (estaesta == null) {
			mgr.persist(est);
		}
	}

	/*
	 * Este metodo permite eliminar un acceso de tipo estacionamiento de la
	 * lista de accesos del Shopping
	 */
	public void deleteAccesoEstacionamiento(EntityManager mgr, String nombre) {
		Estacionamiento est = mgr.find(Estacionamiento.class, nombre);
		if (est != null) {
			mgr.remove(est);
		}
	}

	/*
	 * Devuelve el nombre del acceso en base al elemRf asociado
	 */
	public Acceso getTipoAcceso(EntityManager mgr, String elemRf) {
		Acceso acceso = null;
		Query queryacc = mgr.createQuery(
				"SELECT a FROM Acceso a WHERE a.elementoRF = :elemrf",
				Acceso.class);
		queryacc.setParameter("elemrf", elemRf);
		try {
			acceso = (Acceso) queryacc.getSingleResult();
		} catch (NoResultException e) {
			acceso = null;
		}
		return acceso;

	}

}
