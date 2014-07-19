package com.fortmin.proshopping.logica;

import javax.persistence.EntityManager;

import com.fortmin.proshopping.entidades.ConfigLong;

public class Parametros {
	
	public ConfigLong getParametroNumero(EntityManager mgr, String codparam) {
		ConfigLong param = mgr.find(ConfigLong.class, codparam);
		return param;
	}
	
	public void insertParametroNumero(EntityManager mgr, String nomparam, long valor) {
		ConfigLong cfglong = mgr.find(ConfigLong.class, nomparam);
		if (cfglong == null) {
			cfglong = new ConfigLong(nomparam, valor);
			mgr.persist(cfglong);
		}
	}
	
	public void updateParametroNumero(EntityManager mgr, String nomparam, long valor) {
		ConfigLong cfglong = mgr.find(ConfigLong.class, nomparam);
		if (cfglong != null) {
			cfglong.setValor(valor);
			mgr.persist(cfglong);
		}
	}

	public void deleteParametroNumero(EntityManager mgr, String nomparam) {
		ConfigLong cfglong = mgr.find(ConfigLong.class, nomparam);
		if (cfglong != null) {
			mgr.remove(cfglong);
		}
	}
}
