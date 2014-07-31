package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fortmin.proshopping.entidades.Imagen;
import com.fortmin.proshopping.entidades.Producto;
import com.fortmin.proshopping.entidades.ProductoExtVO;
import com.google.appengine.api.datastore.Blob;

public class Productos {

	/*
	 * Este metodo permite agregar un producto a la lista de productos de un
	 * comercio
	 */
	public void insertProducto(EntityManager mgr, String comercio,
			String codigo, String nombre, float precio, String detalles) {
		Producto producto = null;
		producto = new Producto(comercio, codigo, nombre, precio, detalles);
		Producto prodesta = mgr.find(Producto.class, producto.getClave());
		if (prodesta == null) {
			mgr.persist(producto);
		}
	}

	/*
	 * Este metodo permite eliminar un producto de la lista de productos de un
	 * comercio
	 */
	public void deleteProducto(EntityManager mgr, String comercio, String codigo) {
		Producto producto = new Producto(comercio, codigo);
		producto = mgr.find(Producto.class, producto.getClave());
		if (producto != null) {
			mgr.remove(producto);
		}
	}

	/*
	 * Este metodo permite agregar un producto a la lista de productos de un
	 * comercio
	 */
	public void updateProducto(EntityManager mgr, String comercio,
			String codigo, String nombre, float precio, String detalles) {
		EntityTransaction trans = mgr.getTransaction();
		trans.begin();
		Producto producto = new Producto(comercio, codigo);
		producto = mgr.find(Producto.class, producto.getClave());
		if (producto != null) {
			producto.setNombre(nombre);
			producto.setPrecio(precio);
			producto.setDetalles(detalles);
			mgr.persist(producto);
		}
		trans.commit();
	}

	/* 
	 * Obtener todos los datos del producto incluyendo su imagen
	 */
	public ProductoExtVO getProductoCompleto(EntityManager mgr, String comercio, String codigo) {
		ProductoExtVO resp = null;
		Producto producto = new Producto(comercio, codigo);
		producto = mgr.find(Producto.class, producto.getClave());
		if (producto != null) {
			resp = new ProductoExtVO(producto);
		}
		return resp;
	}

	/*
	 * Cargar la imagen de un producto
	 */
	public void cargarImagen(EntityManager mgr, String comercio, String producto, Imagen img) {
		Producto prod = new Producto(comercio, producto);
		prod = mgr.find(Producto.class, prod.getClave());
		if (prod != null) {
			prod.setImagen(img.getImagen());
			prod.setTipoImagen(img.getTipoImg());
			mgr.persist(prod);
		}
	}

	/*
	 * Obtener la imagen de un producto
	 */
	public Imagen getImagen(EntityManager mgr, String comercio, String producto) {
		Imagen imagen = null;
		Producto prod = new Producto(comercio, producto);
		prod = mgr.find(Producto.class, prod.getClave());
		if (prod != null) {
			imagen = new Imagen(prod.getTipoImagen(), prod.getImagen());
		}
		return imagen;
	}
	
}
