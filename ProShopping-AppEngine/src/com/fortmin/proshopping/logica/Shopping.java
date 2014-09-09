package com.fortmin.proshopping.logica;

import java.util.LinkedList;

import javax.inject.Named;
import javax.persistence.EntityManager;

import com.fortmin.proshopping.entidades.Paquete;
import com.fortmin.proshopping.entidades.Producto;
import com.fortmin.proshopping.entidades.Ubicacion;
import com.fortmin.proshopping.valueobjects.CarritoVO;
import com.fortmin.proshopping.valueobjects.ComprasVO;
import com.fortmin.proshopping.valueobjects.EstacionamientoVO;
import com.fortmin.proshopping.valueobjects.Mensaje;
import com.fortmin.proshopping.valueobjects.PaqueteVO;
import com.fortmin.proshopping.valueobjects.ProductoExtVO;
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
	public Mensaje ingresoEstacionamiento(@Named("elementoRF") String elemRf,
			@Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Accesos accs = new Accesos();
		Mensaje resp;
		resp = accs.ingresoEstacionamiento(mgr, elemRf, usuario);
		mgr.close();
		return resp;
	}

	/*
	 * Egreso del Shopping a través de un estacionamiento
	 */
	@ApiMethod(name = "EgresoEstacionamiento", path = "egreso_estacionamiento")
	public Mensaje egresoEstacionamiento(@Named("elementoRF") String elemRf,
			@Named("usuario") String usuario) {
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
	public Mensaje establecerVisibilidad(@Named("usuario") String usuario,
			@Named("visible") boolean visible) {
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

	/*
	 * Obtener el paquete completo con todos sus productos
	 */
	@ApiMethod(name = "GetPaqueteCompleto", path = "get_paquete_completo")
	public PaqueteVO getPaqueteCompleto(@Named("elementoRF") String elemRf) {
		EntityManager mgr = getEntityManager();
		Paquetes paquetes = new Paquetes();
		PaqueteVO resp = paquetes.getPaqueteCompleto(mgr, elemRf);
		return resp;
	}

	/*
	 * Obtener todos los datos del producto incluyendo su imagen
	 */
	@ApiMethod(name = "GetProductoCompleto", path = "get_producto_completo")
	public ProductoExtVO getProductoCompleto(
			@Named("comercio") String comercio, @Named("codigo") String codigo) {
		EntityManager mgr = getEntityManager();
		Productos prods = new Productos();
		ProductoExtVO resp = prods.getProductoCompleto(mgr, comercio, codigo);
		return resp;
	}

	/*
	 * Obtener calibrado, retorna -9999 si no esta calibrado
	 */
	@ApiMethod(name = "GetCalibradoBeacon", path = "get_calibrado_beacon")
	public Mensaje getCalibradoBeacon(@Named("elementoRF") String elemRf) {
		EntityManager mgr = getEntityManager();
		ElementosRF elems = new ElementosRF();
		Mensaje resp = elems.getCalibradoBeacon(mgr, elemRf);
		return resp;
	}

	/*
	 * Actualizar la posicion del cliente a partir del elemento RF
	 */
	@ApiMethod(name = "ActualizarPosicion", path = "actualizar_posicion")
	public Mensaje actualizarPosicion(@Named("usuario") String usuario,
			@Named("elementoRF") String elemRf, @Named("tipo") String tipo) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		Mensaje resp = clis.actualizarPosicion(mgr, usuario, elemRf, tipo);
		return resp;
	}

	/*
	 * Agregar un item al carrito de compras del cliente
	 */
	@ApiMethod(name = "AgregarItemCarrito", path = "agregar_item_carrito")
	public Mensaje agregarItemCarrito(@Named("usuario") String usuario,
			@Named("paquete") String nompaq) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		Mensaje resp = clis.agregarItemCarrito(mgr, usuario, nompaq);
		return resp;
	}

	/*
	 * Eliminar un item del carrito de compras del cliente
	 */
	@ApiMethod(name = "EliminarItemCarrito", path = "eliminar_item_carrito")
	public Mensaje eliminarItemCarrito(@Named("usuario") String usuario,
			@Named("paquete") String nompaq) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		Mensaje resp = clis.eliminarItemCarrito(mgr, usuario, nompaq);
		return resp;
	}

	/*
	 * Obtener el carrito de compras del cliente completo, con todos sus items
	 */
	@ApiMethod(name = "GetCarritoCompleto", path = "get_carrito_completo")
	public CarritoVO getCarritoCompleto(@Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		CarritoVO resp = clis.getCarritoCompleto(mgr, usuario);
		return resp;
	}

	/*
	 * Checkout del carrito de compras del cliente, es la compra en si
	 */
	@ApiMethod(name = "CheckoutCarrito", path = "checkout_carrito")
	public Mensaje checkoutCarrito(@Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		Mensaje resp = clis.checkoutCarrito(mgr, usuario);
		return resp;
	}

	/*
	 * Lista las compras del cliente
	 */
	@ApiMethod(name = "GetCompras", path = "get_compras")
	public LinkedList<ComprasVO> getCompras(@Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		LinkedList<ComprasVO> resp = clis.getCompras(mgr, usuario);
		return resp;
	}

	/*
	 * Agrega puntos al cliente, utilizada para la transferencia de puntos
	 */
	@ApiMethod(name = "AgregarPuntos", path = "agregar_puntos")
	public Mensaje agregarPuntos(@Named("usuario") String usuario,
			@Named("puntos") int puntos) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		Mensaje resp = clis.agregarPuntos(mgr, usuario, puntos);
		return resp;
	}

	/*
	 * Quita puntos al cliente, utilizada para la transferencia de puntos
	 */
	@ApiMethod(name = "QuitarPuntos", path = "quitar_puntos")
	public Mensaje quitarPuntos(@Named("puntos") int puntos,
			@Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		Mensaje resp = clis.quitarPuntos(mgr, usuario, puntos);
		return resp;
	}

	/*
	 * Quita puntos al cliente, utilizada para la transferencia de puntos
	 */
	@ApiMethod(name = "GetTiempoEstacionamiento", path = "get_tiempo_estacionamiento")
	public EstacionamientoVO getTiempoEstacionamiento(
			@Named("usuario") String usuario) {
		EntityManager mgr = getEntityManager();
		Clientes clis = new Clientes();
		EstacionamientoVO resp = clis.getTiempoEstacionamiento(mgr, usuario);
		return resp;
	}

	/*
	 * Obtener una referencia al EntityManager
	 */
	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}
