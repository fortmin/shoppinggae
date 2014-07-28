package com.fortmin.proshopping.logica;

import java.util.LinkedList;

import javax.inject.Named;
import javax.persistence.EntityManager;

import com.fortmin.proshopping.entidades.Mensaje;
import com.fortmin.proshopping.entidades.Paquete;
import com.fortmin.proshopping.entidades.PaqueteVO;
import com.fortmin.proshopping.entidades.Producto;
import com.fortmin.proshopping.entidades.ProductoVO;
import com.fortmin.proshopping.entidades.Ubicacion;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(name = "shopping", namespace = @ApiNamespace(ownerDomain = "fortmin.com", ownerName = "fortmin.com", packagePath = "proshopping.logica"))
public class Shopping {


	/*
	 * Este metodo permite obtener los datos de una ubicacion del conjunto de
	 * ubicaciones del Shopping
	 */
	@ApiMethod(name = "getubicacion", path = "get_ubicacion")
	public Ubicacion getUbicacion(@Named("ubicacion") String nombre) {
		EntityManager mgr = getEntityManager();
		Ubicaciones ubic = new Ubicaciones();
		Ubicacion resp = ubic.getUbicacion(mgr, nombre);
		mgr.close();
		return resp;
	}


	/*
	 * Obtener los datos del paquete en base a su nombre
	 */
	@ApiMethod(name = "getpaquete", path = "get_paquete")
	public Paquete getPaquete(@Named("paquete") String nompaq) {
		Paquete resp = null;
		EntityManager mgr = getEntityManager();
		Paquetes paqs = new Paquetes();
		Paquete paquete = paqs.getPaquete(mgr, nompaq);
		if (paquete != null)
			resp = paquete;
		mgr.close();
		return resp;
	}


	/*
	 * Dada la identificacion de un elemento RF (Tag, Beacon, ...) se devuelve
	 * el paquete promocional asociado al mismo
	 */
	@ApiMethod(name = "GetPaqueteRf", path = "get_paquete_rf")
	public Paquete getPaqueteRF(@Named("elementoRF") String elemRf) {
		Paquete paqresp = null;
		EntityManager mgr = getEntityManager();
		Paquetes paqs = new Paquetes();
		Paquete paquete = paqs.getPaqueteRF(mgr, elemRf);
		if (paquete != null)
			paqresp = paquete;
		mgr.close();
		return paqresp;
	}

	/*
	 * Obtener la lista de productos de un paquete existente
	 */
	@ApiMethod(name = "GetProductosPaquete", path = "get_productos_paquete")
	public LinkedList<Producto> getProductosPaquete(
			@Named("paquete") String nompaq) {
		LinkedList<Producto> prodlist = new LinkedList<Producto>();
		EntityManager mgr = getEntityManager();
		Paquetes paqs = new Paquetes();
		prodlist = paqs.getProductosPaquete(mgr, nompaq);
		mgr.close();
		return prodlist;
	}

	/*
	 * Ingreso al Shopping a través de un estacionamiento
	 */
	@ApiMethod(name = "IngresoEstacionamiento", path = "ingreso_estacionamiento")
	public Mensaje ingresoEstacionamiento(@Named("elementoRF") String elemRf, @Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Accesos accs = new Accesos();
		Mensaje resp = accs.ingresoEstacionamiento(mgr, elemRf, usuario);
		mgr.close();
		return resp;
	}
	
	/*
	 * Egreso del Shopping a través de un estacionamiento
	 */
	@ApiMethod(name = "EgresoEstacionamiento", path = "egreso_estacionamiento")
	public Mensaje egresoEstacionamiento(@Named("elementoRF") String elemRf, @Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Accesos accs = new Accesos();
		Mensaje resp = accs.egresoEstacionamiento(mgr, elemRf, usuario);
		mgr.close();
		return resp;
	}
	
	/*
	 * Establecer la visibilidad del cliente para con los amigos
	 */
	@ApiMethod(name = "EstablecerVisibilidad", path = "establecer_visibilidad")
	public Mensaje establecerVisibilidad(@Named("usuario") String usuario, @Named("visible") boolean visible) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		Mensaje resp = clis.establecerVisibilidadCliente(mgr, usuario, visible);
		mgr.close();
		return resp;
	}

	/*
	 * Obtener el estado de visibilidad del cliente para con los amigos
	 */
	@ApiMethod(name = "GetVisibilidadCliente", path = "get_visibilidad_cliente")
	public Mensaje getVisibilidadCliente(@Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		Mensaje resp = clis.getVisibilidadCliente(mgr, usuario);
		mgr.close();
		return resp;
	}

	/*
	 * Obtener el puntaje actual del cliente
	 */
	@ApiMethod(name = "GetPuntajeCliente", path = "get_puntaje_cliente")
	public Mensaje getPuntajeCliente(@Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		Mensaje resp = clis.getPuntajeCliente(mgr, usuario);
		mgr.close();
		return resp;
	}
	
	@ApiMethod(name = "GetPaqueteCompleto", path = "get_paquete_completo") 
	public PaqueteVO getPaqueteCompleto(@Named("elementoRF") String elemRf) {
		EntityManager mgr = getEntityManager();
		Paquetes paquetes = new Paquetes();
		PaqueteVO resp = paquetes.getPaqueteCompleto(mgr, elemRf);
		return resp;
	}

	/*
	 * Obtener una referencia al EntityManager
	 */
	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}


}
