package com.fortmin.proshopping.logica;

import java.util.LinkedList;

import javax.inject.Named;
import javax.persistence.EntityManager;

import com.fortmin.proshopping.persistencia.Paquete;
import com.fortmin.proshopping.persistencia.Producto;
import com.fortmin.proshopping.persistencia.Ubicacion;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

@Api(name = "shopping", namespace = @ApiNamespace(ownerDomain = "fortmin.com", ownerName = "fortmin.com", packagePath = "proshopping"))
public class Shopping {

	/*
	 * Este metodo permite agregar una ubicacion al conjunto de ubicaciones del
	 * Shopping
	 */
	@ApiMethod(name = "insertubicacion", path = "insert_ubicacion")
	public void insertUbicacion(@Named("nombre") String nombre,
			@Named("edificio") String edificio, @Named("piso") int piso,
			@Named("sector") String sector, @Named("area") String area,
			@Named("lugar") String lugar) {
		EntityManager mgr = getEntityManager();
		Ubicaciones ubic = new Ubicaciones();
		ubic.insertUbicacion(mgr, nombre, edificio, piso, sector, area, lugar);
		mgr.close();
	}

	/*
	 * Este metodo permite actualizar una ubicacion del conjunto de ubicaciones
	 * del Shopping
	 */
	@ApiMethod(name = "updateubicacion", path = "update_ubicacion")
	public void updateUbicacion(@Named("nombre") String nombre,
			@Named("edificio") String edificio, @Named("piso") int piso,
			@Named("sector") String sector, @Named("area") String area,
			@Named("lugar") String lugar) {
		EntityManager mgr = getEntityManager();
		Ubicaciones ubic = new Ubicaciones();
		ubic.updateUbicacion(mgr, nombre, edificio, piso, sector, area, lugar);
		mgr.close();
	}

	/*
	 * Este metodo permite eliminar una ubicacion del conjunto de ubicaciones
	 * del Shopping
	 */
	@ApiMethod(name = "deleteubicacion", path = "delete_ubicacion")
	public void deleteUbicacion(@Named("ubicacion") String nomubi) {
		EntityManager mgr = getEntityManager();
		Ubicaciones ubic = new Ubicaciones();
		ubic.deleteUbicacion(mgr, nomubi);
		mgr.close();
	}

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
	 * Este metodo agrega un comercio al conjunto de comercios del Shopping
	 */
	@ApiMethod(name = "insertcomercio", path = "insert_comercio")
	public void insertComercio(@Named("comercio") String nomcom,
			@Named("ubicacion") String ubicacion) {
		EntityManager mgr = getEntityManager();
		Comercios comer = new Comercios();
		comer.insertComercio(mgr, nomcom, ubicacion);
		mgr.close();
	}

	/*
	 * Este metodo actualiza los datos de un comercio
	 */
	@ApiMethod(name = "updatecomercio", path = "update_comercio")
	public void updateComercio(@Named("comercio") String nomcom,
			@Named("ubicacion") String ubicacion) {
		EntityManager mgr = getEntityManager();
		Comercios comer = new Comercios();
		comer.updateComercio(mgr, nomcom, ubicacion);
		mgr.close();
	}

	/*
	 * Este metodo da de baja un comercio del conjunto de comercios del Shopping
	 */
	@ApiMethod(name = "deletecomercio", path = "delete_comercio")
	public void deleteComercio(@Named("comercio") String nomcom) {
		EntityManager mgr = getEntityManager();
		Comercios comer = new Comercios();
		comer.deleteComercio(mgr, nomcom);
		mgr.close();
	}

	/*
	 * Este metodo permite agregar un producto a la lista de productos de un
	 * comercio
	 */
	@ApiMethod(name = "insertproducto", path = "insert_producto")
	public void insertProducto(@Named("comercio") String comercio,
			@Named("codProd") String codigo, @Named("nomProd") String nombre,
			@Named("precioProd") float precio) {
		EntityManager mgr = getEntityManager();
		Productos prods = new Productos();
		prods.insertProducto(mgr, comercio, codigo, nombre, precio);
		mgr.close();
	}

	/*
	 * Este metodo permite eliminar un producto de la lista de productos de un
	 * comercio
	 */
	@ApiMethod(name = "deleteproducto", path = "delete_producto")
	public void deleteProducto(@Named("comercio") String comercio,
			@Named("codProd") String codigo) {
		EntityManager mgr = getEntityManager();
		Productos prods = new Productos();
		prods.deleteProducto(mgr, comercio, codigo);
		mgr.close();
	}

	/*
	 * Este metodo permite agregar un producto a la lista de productos de un
	 * comercio
	 */
	@ApiMethod(name = "updateproducto", path = "update_producto")
	public void updateProducto(@Named("comercio") String comercio,
			@Named("codProd") String codigo, @Named("nomProd") String nombre,
			@Named("precioProd") float precio) {
		EntityManager mgr = getEntityManager();
		Productos prods = new Productos();
		prods.updateProducto(mgr, comercio, codigo, nombre, precio);
		mgr.close();
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
	 * Crear un paquete nuevo con una lista de productos vacia y luego los
	 * productos deben agregarse con insertProductoPaquete
	 */
	@ApiMethod(name = "insertpaquete", path = "insert_paquete")
	public void insertPaquete(@Named("paquete") String nompaq,
			@Named("puntos") int puntos, @Named("precio") float precio,
			@Named("elementoRf") String elemRf) {
		EntityManager mgr = getEntityManager();
		Paquetes paqs = new Paquetes();
		paqs.insertPaquete(mgr, nompaq, puntos, precio, elemRf);
		mgr.close();
	}

	/*
	 * Eliminar un paquete existente de un comercio junto a todos sus datos
	 */
	@ApiMethod(name = "deletepaquete", path = "delete_paquete")
	public void deletePaquete(@Named("paquete") String nompaq) {
		EntityManager mgr = getEntityManager();
		Paquetes paqs = new Paquetes();
		paqs.deletePaquete(mgr, nompaq);
		mgr.close();
	}

	/*
	 * Actualizar un paquete existente con nuevos valores de precio y puntos
	 */
	@ApiMethod(name = "updatepaquete", path = "update_paquete")
	public void updatePaquete(@Named("paquete") String nompaq,
			@Named("puntos") int puntos, @Named("precio") float precio,
			@Named("elementoRf") String elemRf) {
		EntityManager mgr = getEntityManager();
		Paquetes paqs = new Paquetes();
		paqs.updatePaquete(mgr, nompaq, puntos, precio, elemRf);
		mgr.close();
	}

	/*
	 * Insertar un producto en un paquete ya existente
	 */
	@ApiMethod(name = "insertproductopaquete", path = "insert_producto_paquete")
	public void insertProductoPaquete(@Named("paquete") String nompaq,
			@Named("comercio") String comercio,
			@Named("codProducto") String codigo) {
		EntityManager mgr = getEntityManager();
		Paquetes paqs = new Paquetes();
		paqs.insertProductoPaquete(mgr, nompaq, comercio, codigo);
		mgr.close();
	}

	/*
	 * Borrar un producto de un paquete ya existente
	 */
	@ApiMethod(name = "deleteproductopaquete", path = "delete_producto_paquete")
	public void deleteProductoPaquete(@Named("paquete") String nompaq,
			@Named("comercio") String comercio,
			@Named("codProducto") String codigo) {
		EntityManager mgr = getEntityManager();
		Paquetes paqs = new Paquetes();
		paqs.deleteProductoPaquete(mgr, nompaq, comercio, codigo);
		mgr.close();
	}

	/*
	 * Insertar un Tag NFC junto con sus datos
	 */
	@ApiMethod(name = "inserttag", path = "insert_tag")
	public void insertTag(@Named("tagid") String id,
			@Named("ubicacion") String ubicacion,
			@Named("tipocontenido") String tipo) {
		EntityManager mgr = getEntityManager();
		ElementosRF elems = new ElementosRF();
		elems.insertTag(mgr, id, ubicacion, tipo);
		mgr.close();
	}

	/*
	 * Actualizar los datos de un Tag NFC ya existente
	 */
	@ApiMethod(name = "updatetag", path = "update_tag")
	public void updateTag(@Named("tagid") String id,
			@Named("ubicacion") String ubicacion,
			@Named("tipocontenido") String tipo) {
		EntityManager mgr = getEntityManager();
		ElementosRF elems = new ElementosRF();
		elems.updateTag(mgr, id, ubicacion, tipo);
		mgr.close();
	}

	/*
	 * Elimina un Tag NFC existente
	 */
	@ApiMethod(name = "deletetag", path = "delete_tag")
	public void deleteTag(@Named("tagid") String id) {
		EntityManager mgr = getEntityManager();
		ElementosRF elems = new ElementosRF();
		elems.deleteTag(mgr, id);
		mgr.close();
	}

	/*
	 * Insertar un nuevo Beacon
	 */
	@ApiMethod(name = "insertbeacon", path = "insert_beacon")
	public void insertBeacon(@Named("beaconid") String id,
			@Named("ubicacion") String ubicacion, @Named("uuid") String uuid,
			@Named("major") int major, @Named("minor") int minor,
			@Named("rssi") int rssi) {
		EntityManager mgr = getEntityManager();
		ElementosRF elems = new ElementosRF();
		elems.insertBeacon(mgr, id, ubicacion, uuid, major, minor, rssi);
		mgr.close();
	}

	/*
	 * Actualizar los datos de un Beacon existente
	 */
	@ApiMethod(name = "updatebeacon", path = "update_beacon")
	public void updateBeacon(@Named("beaconid") String id,
			@Named("ubicacion") String ubicacion, @Named("uuid") String uuid,
			@Named("major") int major, @Named("minor") int minor,
			@Named("rssi") int rssi) {
		EntityManager mgr = getEntityManager();
		ElementosRF elems = new ElementosRF();
		elems.updateBeacon(mgr, id, ubicacion, uuid, major, minor, rssi);
		mgr.close();
	}

	/*
	 * Elimina un Beacon existente
	 */
	@ApiMethod(name = "deletebeacon", path = "delete_beacon")
	public void deleteBeacon(@Named("beaconid") String id) {
		EntityManager mgr = getEntityManager();
		ElementosRF elems = new ElementosRF();
		elems.deleteBeacon(mgr, id);
		mgr.close();
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
