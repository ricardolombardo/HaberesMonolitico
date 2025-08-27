package com.HaberesMonolitico.entities;

import java.util.Hashtable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TITULO")  // valor que se guarda en la columna TIPO
public class TareaGeneracionTitulos extends Tarea{
	
	@Override
    public String getSP() {
    	return "PRC_GEN_TITULO";
    }

	@Override
    public Hashtable<String, Object> getParametros(Long idLiquidacion){
		Hashtable<String, Object> parametros =new Hashtable<String, Object>();
		parametros.put("idLiquidacion", idLiquidacion);
    	return parametros;
    }
	
}
