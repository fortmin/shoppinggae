package com.fortmin.proshopping.logica;

import java.util.Date;

public class Utils {
	
	/*
	 * Convierte la fecha a formato string de acuerdo al formato que se quiera
	 * Ejemplo de formato: "MM-dd-yyyy"
	 */
	public String obtenerFechaString(Date fecha, String formato) {
		String fechastr = new java.text.SimpleDateFormat(formato).format(fecha);
		return fechastr;
	}
	
	/*
	 * Convierte la fecha a formato string de acuerdo al formato que se quiera
	 * Ejemplo de formato: "hh:mm:ss"
	 */
	public String obtenerHoraString(Date fecha, String formato) {
		String horastr = new java.text.SimpleDateFormat(formato).format(fecha);
		return horastr;
	}
	
	/*
	 * Devuelve la diferencia entre 2 fechas en segundos
	 */
	public long diffFechasSegundos(Date fechaDesde, Date fechaHasta) {
		long resp = 0;
		if (fechaHasta.getTime() > fechaDesde.getTime()) {
			resp = (fechaHasta.getTime() - fechaDesde.getTime()) / 1000;
		}
		return resp;
	}

}
