package com.fortmin.proshopping.valueobjects;

import com.google.appengine.api.datastore.Blob;

public class ImagenVO {

	private Blob imagen;
	
	public ImagenVO() {
	}
	
	public Blob getImagen() {
		return imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

}
