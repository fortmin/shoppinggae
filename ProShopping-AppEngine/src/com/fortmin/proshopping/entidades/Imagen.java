package com.fortmin.proshopping.entidades;

import com.google.appengine.api.datastore.Blob;

public class Imagen {

	private Blob imagen;
	
	public Imagen() {
	}
	
	public Blob getImagen() {
		return imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

}
