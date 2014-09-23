/**
 * 
 */
package com.fortmin.proshopping.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fortmin.proshopping.entidades.Cliente;
import com.fortmin.proshopping.logica.Clientes;
import com.fortmin.proshopping.logica.EMF;

/**
 * @author fminos
 * 
 */
public class ClienteTest {

	static Clientes clientes;
	static EntityManager mgr;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clientes = new Clientes();
		mgr = EMF.get().createEntityManager();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.fortmin.proshopping.logica.Clientes#getCliente(javax.persistence.EntityManager, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCliente() {
		Cliente cliente = clientes.getCliente(mgr, "test");
		assertNotNull("No devolvio ningun cliente", cliente);
		if (cliente != null) {
			assertEquals("Direccion de mail = test@gmail.com",
					"test@gmail.com", cliente.getEmail());
			assertEquals("Nombre de usuario = Cliente para Test",
					"Cliente para Test", cliente.getNombre());
			assertEquals("Clave = mundial", "mundial", cliente.getClave());
		}
	}

	/**
	 * Test method for
	 * {@link com.fortmin.proshopping.logica.Clientes#clientePresente(javax.persistence.EntityManager, java.lang.String)}
	 * .
	 */
	@Test
	public void testPresenciaCliente() {
		// clientePresente
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.fortmin.proshopping.logica.Clientes#agregarPuntos(javax.persistence.EntityManager, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testPuntajeCliente() {
		// agregarPuntos
		// quitarPuntos
		// getPuntajeCliente
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.fortmin.proshopping.logica.Clientes#establecerVisibilidadCliente(javax.persistence.EntityManager, java.lang.String, boolean)}
	 * .
	 */
	@Test
	public void testVisibilidadCliente() {
		// establecerVisibilidadCliente
		// getVisibilidadCliente
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.fortmin.proshopping.logica.Clientes#actualizarPosicion(javax.persistence.EntityManager, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testPosicionCliente() {
		// actualizarPosicion
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.fortmin.proshopping.logica.Clientes#agregarItemCarrito(javax.persistence.EntityManager, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testAgregarItemCarrito() {
		// agregarItemCarrito
		// eliminarItemCarrito
		// getCarritoCompleto
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.fortmin.proshopping.logica.Clientes#getCompras(javax.persistence.EntityManager, java.lang.String)}
	 * .
	 */
	@Test
	public void testGetCompras() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link com.fortmin.proshopping.logica.Clientes#obtenerAmigosANotificar(javax.persistence.EntityManager, java.lang.String)}
	 * .
	 */
	@Test
	public void testObtenerAmigosANotificar() {
		fail("Not yet implemented");
	}

}
