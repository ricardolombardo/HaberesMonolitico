package com.HaberesMonolitico.services;

import com.HaberesMonolitico.entities.TareaLiquidacion;
import com.HaberesMonoliitico.model.TareaEjecutable;
import com.HaberesMonolitico.entities.Tarea;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EjecutorTarea {

    public void ejecutarTareas(List<TareaLiquidacion> tareasLiquidacion) {
        if (tareasLiquidacion == null || tareasLiquidacion.isEmpty()) {
            System.out.println("No hay tareas para ejecutar.");
            return;
        }

        // Ordenar por el campo ORDEN
        tareasLiquidacion.sort(Comparator.comparing(TareaLiquidacion::getOrden));

        // Ejecutar cada tarea
        for (TareaLiquidacion tl : tareasLiquidacion) {
            ejecutar(tl);
        }
    }

    private void ejecutar(TareaLiquidacion tareaLiquidacion) {
        Tarea tarea = tareaLiquidacion.getTarea();

        System.out.println("Ejecutando tarea: " + tarea.getNombre() 
                + " (orden " + tareaLiquidacion.getOrden() + ")");

        if (tarea instanceof TareaEjecutable ejecutable) {
            Boolean resultado = ejecutable.ejecutar();
            tareaLiquidacion.setEstadoEjecucion(resultado ? "EJECUTADA" : "ERROR");
        } else {
            tareaLiquidacion.setEstadoEjecucion("NO_IMPLEMENTADA");
            System.out.println("⚠ La tarea " + tarea.getNombre() + " no implementa TareaEjecutable.");
        }
    }
}
