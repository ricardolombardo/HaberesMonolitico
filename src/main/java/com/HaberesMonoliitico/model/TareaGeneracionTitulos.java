package com.HaberesMonoliitico.model;

import org.springframework.beans.factory.annotation.Autowired;
import com.HaberesMonolitico.DAO.LiquidacionDAO;
import com.HaberesMonolitico.entities.Tarea;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PLANTA_PERMANENTE")  // valor que se guarda en la columna TIPO
public class TareaGeneracionTitulos extends Tarea implements TareaEjecutable{

	
	@Autowired
	private LiquidacionDAO liquidacionDAO; 
	
	private Long idLiquidacion;
	
	@Override
	public Boolean ejecutar() {
		liquidacionDAO.ejecutarSPGenerarTitulo(idLiquidacion);
		return null;
	}

	public Long getIdLiquidacion() {
		return idLiquidacion;
	}

	public void setIdLiquidacion(Long idLiquidacion) {
		this.idLiquidacion = idLiquidacion;
	}
	
}
