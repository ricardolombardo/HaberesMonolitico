package com.HaberesMonolitico.services;

import java.util.ArrayList;
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
	public List<TareaLiquidacion> actualizarTareasDeLiquidacionConDTO(
	        List<TareaLiquidacionUpdateDTO> dtos, Long idLiquidacion) {

	    Liquidacion liquidacion = liquidacionRepository.findById(idLiquidacion)
	            .orElseThrow(() -> new RuntimeException("Liquidación no encontrada"));

	    List<TareaLiquidacion> result = new ArrayList<>();

	    for (TareaLiquidacionUpdateDTO dto : dtos) {
	        Tarea tarea = tareaRepository.findById(dto.getIdTarea())
	                .orElseThrow(() -> new RuntimeException("Tarea no encontrada: " + dto.getIdTarea()));

	        // Buscar si ya existe la relación TareaLiquidacion para esta liquidacion y tarea
	        TareaLiquidacion tl = tareaLiquidacionRepository
	                .findByTareaIdAndLiquidacionId(dto.getIdTarea(), idLiquidacion)
	                .orElse(new TareaLiquidacion());

	        tl.setTarea(tarea);
	        tl.setLiquidacion(liquidacion);
	        tl.setOrden(dto.getOrden());
	        tl.setEstadoEjecucion(dto.getEstadoEjecucion() != null ? dto.getEstadoEjecucion() : "PENDIENTE");

	        result.add(tareaLiquidacionRepository.save(tl));
	    }

	    return result;
	}




}
