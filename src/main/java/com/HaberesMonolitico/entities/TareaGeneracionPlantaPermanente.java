package com.HaberesMonolitico.entities;

import java.util.Hashtable;

import com.HaberesMonolitico.entities.Tarea;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PLANTA_PERMANENTE")  // valor que se guarda en la columna TIPO
public class TareaGeneracionPlantaPermanente extends Tarea{
	
	@Override
    public String getSP() {
    	return "PRC_GEN_BASICO";
    }

	@Override
    public Hashtable<String, Object> getParametros(Long idLiquidacion){
		Hashtable<String, Object> parametros =new Hashtable<String, Object>();
		parametros.put("idLiquidacion", idLiquidacion);
    	return parametros;
    }

}
