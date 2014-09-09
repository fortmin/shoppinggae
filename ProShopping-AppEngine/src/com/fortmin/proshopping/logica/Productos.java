package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fortmin.proshopping.entidades.Producto;
import com.fortmin.proshopping.valueobjects.ImagenVO;
import com.fortmin.proshopping.valueobjects.ProductoExtVO;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.Image;

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
	public ProductoExtVO getProductoCompleto(EntityManager mgr,
			String comercio, String codigo) {
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
	public void cargarImagen(EntityManager mgr, String comercio,
			String producto, ImagenVO imagen) {
		Producto prod = new Producto(comercio, producto);
		prod = mgr.find(Producto.class, prod.getClave());
		if (prod != null) {
			prod.setImagen(imagen.getImagen());
			prod.setTipoImagen("image/png");
			mgr.persist(prod);
		}
	}

	/*
	 * Cargar la imagen de un producto
	 */
	public void cargarImagenBlob(EntityManager mgr, String comercio,
			String producto, ImagenVO imagen) {
		Producto prod = new Producto(comercio, producto);
		prod = mgr.find(Producto.class, prod.getClave());
		if (prod != null) {
			prod.setImagen(imagen.getImagen());
			prod.setTipoImagen("image/png");
			mgr.persist(prod);
		}
	}

	/*
	 * Cargar la imagen de un producto utilizando el Image API
	 */
	public void cargarImagenImage(EntityManager mgr, String comercio,
			String producto, Image imagen) {
		Producto prod = new Producto(comercio, producto);
		prod = mgr.find(Producto.class, prod.getClave());
		if (prod != null) {
			prod.setImagen(new Blob(imagen.getImageData()));
			prod.setTipoImagen("image/png");
			mgr.persist(prod);
		}
	}

	/*
	 * Obtener la imagen de un producto
	 */
	public ImagenVO getImagen(EntityManager mgr, String comercio, String producto) {
		ImagenVO imagen = null;
		Producto prod = new Producto(comercio, producto);
		prod = mgr.find(Producto.class, prod.getClave());
		if (prod != null) {
			imagen = new ImagenVO();
			imagen.setImagen(prod.getImagen());
		}
		return imagen;
	}

}
