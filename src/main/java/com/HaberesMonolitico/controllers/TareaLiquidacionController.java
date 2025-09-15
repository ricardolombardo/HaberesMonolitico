package com.HaberesMonolitico.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HaberesMonolitico.DTO.TareaLiquidacionDTO;
import com.HaberesMonolitico.DTO.TareaLiquidacionUpdateDTO;
import com.HaberesMonolitico.entities.TareaLiquidacion;
import com.HaberesMonolitico.services.TareaLiquidacionService;

@RestController
@RequestMapping("/tareaLiquidacion")
public class TareaLiquidacionController {
	
	@Autowired
	TareaLiquidacionService tareaLiquidacionService;
	
	@GetMapping("/getAll")
	public List<TareaLiquidacion> getAll(){
		return tareaLiquidacionService.listarTodas();
	}
	
	@GetMapping("/porLiquidacion/{idLiquidacion}")
	public List<TareaLiquidacionDTO> getTareasDeUnaLiquidacion(@PathVariable("idLiquidacion") Long idTarea) {
	    return tareaLiquidacionService.findByLiquidacionId(idTarea)
	    		.stream()
                .map(TareaLiquidacionDTO::fromEntity)
                .toList();
	}
	
	@PutMapping("/actualizarPorLiquidacion")
	public List<TareaLiquidacionDTO> actualizarTareas(@RequestBody List<TareaLiquidacionUpdateDTO> tareasUpdate) {
	    if (tareasUpdate == null || tareasUpdate.isEmpty()) {
	        throw new RuntimeException("No se recibieron tareas para actualizar");
	    }

	    Long idLiquidacion = tareasUpdate.get(0).getIdLiquidacion();

	    // Llamar al servicio para reemplazar las tareas
	    List<TareaLiquidacion> tareasActualizadas = tareaLiquidacionService.actualizarTareasDeLiquidacionConDTO(tareasUpdate, idLiquidacion);

	    // Mapear a DTO de salida
	    return tareasActualizadas.stream()
	            .map(TareaLiquidacionDTO::fromEntity)
	            .toList();
	}

}
