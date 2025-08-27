package com.HaberesMonolitico.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HaberesMonolitico.entities.Liquidacion;
import com.HaberesMonolitico.services.EjecutorTarea;
import com.HaberesMonolitico.services.LiquidacionService;
import com.HaberesMonolitico.services.TareaLiquidacionService;

@RestController
@RequestMapping("/liquidaciones")
public class LiquidacionController {
	
	@Autowired
	private LiquidacionService liquidacionService;
	
	@Autowired
	private TareaLiquidacionService tareaLiquidacionService;
	
	@Autowired
	private EjecutorTarea ejecutorTarea;
	
	@GetMapping("/getAll")
	public List<Liquidacion> getAll(){
		return liquidacionService.listarLiquidaciones();
	}
	
	@GetMapping("/fecha/{anio}/{mes}")
	public List<Liquidacion> getLiquidacionesPorAnioMes(
	        @PathVariable ("anio")int anio,
	        @PathVariable ("mes")int mes) {
	    return liquidacionService.listarLiquidacionesPorAnioYMes(anio, mes);
	}
	
	@GetMapping("/rango/{anioDesde}/{mesDesde}/{anioHasta}/{mesHasta}")
	public List<Liquidacion> getLiquidacionesPorRango(
	        @PathVariable("anioDesde") int anioDesde,
	        @PathVariable("mesDesde") int mesDesde,
	        @PathVariable("anioHasta") int anioHasta,
	        @PathVariable("mesHasta") int mesHasta) {
	    return liquidacionService.listarLiquidacionesPorRango(anioDesde, mesDesde, anioHasta, mesHasta);
	}
	
    @GetMapping("/{id}")
	public Optional<Liquidacion> obtenerPorId(@PathVariable("id") Long id) {
		return liquidacionService.obtenerLiquidacionPorId(id);
	}
	
	@PostMapping
	public Liquidacion crearLiquidacion(@RequestBody Liquidacion liquidacion) {
		return liquidacionService.guardarLiquidacion(liquidacion);
	}
	
	@GetMapping("/liquidar/{liquidacionId}")
	public void liquidar(@PathVariable("liquidacionId") Long liquidacionId) {
		Optional<Liquidacion> liquidacion=liquidacionService.obtenerLiquidacionPorId(liquidacionId);
		if(liquidacion.isPresent()) {
			liquidacionService.generarTabulados(liquidacionId);
			//liquidacionService.generarBasicos(liquidacionId);
			//liquidacionService.generarTitulos(liquidacionId);
			
			ejecutorTarea.ejecutarTareas(liquidacion.get().getTareasLiquidacion(),liquidacionId);
			
			liquidacion.get().setLiquidada(Boolean.TRUE);
			liquidacionService.guardarLiquidacion(liquidacion.get());
		}
	}

}
