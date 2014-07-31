package com.fortmin.proshopping.entidades;

import com.google.appengine.api.datastore.Blob;

public class Imagen {

	private String tipoImg;
	private Blob imagen;
	
	public Imagen(String tipoImg, Blob imagen) {
		this.tipoImg = tipoImg;
		this.imagen = imagen;
	}

	public String getTipoImg() {
		return tipoImg;
	}

	public void setTipoImg(String tipoImg) {
		this.tipoImg = tipoImg;
	}

	public Blob getImagen() {
		return imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}

}
