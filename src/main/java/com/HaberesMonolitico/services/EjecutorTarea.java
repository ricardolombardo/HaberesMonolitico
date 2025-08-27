package com.HaberesMonolitico.services;

import com.HaberesMonolitico.entities.TareaLiquidacion;
import com.HaberesMonolitico.DAO.GenericDAOExecutor;
import com.HaberesMonolitico.entities.Tarea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;

@Service
public class EjecutorTarea {
	
	@Autowired
	GenericDAOExecutor genericDAOExecutor;

    public void ejecutarTareas(List<TareaLiquidacion> tareasLiquidacion,Long idLiquidacion) {
        if (tareasLiquidacion == null || tareasLiquidacion.isEmpty()) {
            System.out.println("No hay tareas para ejecutar.");
            return;
        }

        tareasLiquidacion.sort(Comparator.comparing(TareaLiquidacion::getOrden));

        for (TareaLiquidacion tl : tareasLiquidacion) {
            ejecutar(tl,idLiquidacion);
        }
    }

    private void ejecutar(TareaLiquidacion tareaLiquidacion,Long idLiquidacion) {
        Tarea tarea = tareaLiquidacion.getTarea();
    	System.out.println("Clase real de tarea: " + tarea.getClass().getName());
    	Hashtable<String, Object> parametros=tarea.getParametros(idLiquidacion);
        String sp=tarea.getSP();
        genericDAOExecutor.ejecutarSP(tarea.getSP(), parametros);
    }
}
