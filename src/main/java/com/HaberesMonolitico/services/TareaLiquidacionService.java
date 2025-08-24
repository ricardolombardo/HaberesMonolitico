package com.HaberesMonolitico.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HaberesMonolitico.DTO.TareaLiquidacionUpdateDTO;
import com.HaberesMonolitico.entities.Liquidacion;
import com.HaberesMonolitico.entities.Tarea;
import com.HaberesMonolitico.entities.TareaLiquidacion;
import com.HaberesMonolitico.repositories.LiquidacionRepository;
import com.HaberesMonolitico.repositories.TareaLiquidacionRepository;
import com.HaberesMonolitico.repositories.TareaRepository;

import jakarta.transaction.Transactional;

@Service
public class TareaLiquidacionService {
	
	@Autowired
	private TareaLiquidacionRepository tareaLiquidacionRepository;
	
	@Autowired
	private TareaRepository tareaRepository;
	
	@Autowired
	private LiquidacionRepository liquidacionRepository;
	
	public List<TareaLiquidacion> listarTodas() {
		return tareaLiquidacionRepository.findAll();
	}

	public List<TareaLiquidacion> findByLiquidacionId(Long idLiquidacion) {
		return tareaLiquidacionRepository.findByLiquidacionId(idLiquidacion);
	}
	
	@Transactional
	public List<TareaLiquidacion> actualizarTareasDeLiquidacionConDTO(List<TareaLiquidacionUpdateDTO> dtos, Long idLiquidacion) {
	    Liquidacion liquidacion = liquidacionRepository.findById(idLiquidacion)
	            .orElseThrow(() -> new RuntimeException("Liquidación no encontrada"));

	    // Borrar las relaciones actuales
	    List<TareaLiquidacion> actuales = tareaLiquidacionRepository.findByLiquidacionId(idLiquidacion);
	    tareaLiquidacionRepository.deleteAll(actuales);

	    // Crear nuevas relaciones usando orden y estado que vienen del DTO
	    List<TareaLiquidacion> nuevas = dtos.stream().map(dto -> {
	        Tarea tarea = tareaRepository.findById(dto.getIdTarea())
	                .orElseThrow(() -> new RuntimeException("Tarea no encontrada: " + dto.getIdTarea()));
	        TareaLiquidacion tl = new TareaLiquidacion();
	        tl.setTarea(tarea);
	        tl.setLiquidacion(liquidacion);
	        tl.setOrden(dto.getOrden());
	        tl.setEstadoEjecucion(dto.getEstadoEjecucion());
	        return tl;
	    }).toList();

	    return tareaLiquidacionRepository.saveAll(nuevas);
	}


}
