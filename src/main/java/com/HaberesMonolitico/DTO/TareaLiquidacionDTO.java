package com.HaberesMonolitico.DTO;

import com.HaberesMonolitico.entities.TareaLiquidacion;

public record TareaLiquidacionDTO(
	    Integer id,
	    Integer orden,
	    String estadoEjecucion,
	    String nombreTarea,
	    String descripcionTarea
	) {
	    public static TareaLiquidacionDTO fromEntity(TareaLiquidacion tl) {
	        return new TareaLiquidacionDTO(
	            tl.getId(),
	            tl.getOrden(),
	            tl.getEstadoEjecucion(),
	            tl.getTarea().getNombre(),
	            tl.getTarea().getDescripcion()
	        );
	    }
	}
