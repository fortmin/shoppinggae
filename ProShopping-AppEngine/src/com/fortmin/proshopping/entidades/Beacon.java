package com.fortmin.proshopping.entidades;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("BEACON")
public class Beacon extends ElementoRF {

    private String uuid;
    private int major;
    private int minor;
    private int rssi;
    private int calibre;
    private boolean calibrado;

    public Beacon(String id) {
	super(id);
    }

    public Beacon(String id, String ubicacion) {
	super(id, ubicacion);
    }

    public Beacon(String id, String ubicacion, String uuid, int major, int minor) {
	super(id, ubicacion);
	this.uuid = uuid;
	this.major = major;
	this.minor = minor;
	this.rssi = 0;
	this.calibre = 0;
	this.calibrado = false;
    }

    public Beacon(String id, String ubicacion, String uuid, int major,
	    int minor, int rssi) {
	super(id, ubicacion);
	this.uuid = uuid;
	this.major = major;
	this.minor = minor;
	this.rssi = rssi;
	this.calibre = 0;
	this.calibrado = false;
    }

    @Override
    public String getTipo() {
	return "BEACON";
    }

    public String getUuid() {
	return uuid;
    }

    public void setUuid(String uuid) {
	this.uuid = uuid;
    }

    public int getMajor() {
	return major;
    }

    public void setMajor(int major) {
	this.major = major;
    }

    public int getMinor() {
	return minor;
    }

    public void setMinor(int minor) {
	this.minor = minor;
    }

    public int getRssi() {
	return rssi;
    }

    public void setRssi(int rssi) {
	this.rssi = rssi;
    }

    public int getCalibre() {
	return calibre;
    }

    public void setCalibre(int calibre) {
	this.calibre = calibre;
    }

    public boolean isCalibrado() {
	return calibrado;
    }

    public void setCalibrado(boolean calibrado) {
	this.calibrado = calibrado;
    }

}
