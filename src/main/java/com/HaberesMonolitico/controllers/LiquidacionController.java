package com.HaberesMonolitico.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

@RestController
@RequestMapping("/liquidaciones")
public class LiquidacionController {
	
	@Autowired
	private LiquidacionService liquidacionService;
	
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
			ejecutorTarea.ejecutarTareas(liquidacion.get().getTareasLiquidacion(),liquidacionId);
			
			liquidacion.get().setLiquidada(Boolean.TRUE);
			liquidacionService.guardarLiquidacion(liquidacion.get());
		}
	}
	
	
	@GetMapping("/reporte/{anioDesde}/{mesDesde}/{anioHasta}/{mesHasta}")
	public Map<String, Object> generarReporteLiquidaciones(
	        @PathVariable("anioDesde") int anioDesde,
	        @PathVariable("mesDesde") int mesDesde,
	        @PathVariable("anioHasta") int anioHasta,
	        @PathVariable("mesHasta") int mesHasta) {

	    List<Object[]> resultados = liquidacionService.generarReporteLiquidaciones(
	            anioDesde, mesDesde, anioHasta, mesHasta);

	    Map<String, Object> respuesta = new HashMap<>();

	    if (resultados.isEmpty()) {
	        respuesta.put("mensaje", "No se encontraron liquidaciones en el rango.");
	        respuesta.put("cantidadRegistros", 0);
	        respuesta.put("detalle", Collections.emptyList());
	        return respuesta;
	    }

	    respuesta.put("mensaje", "Reporte generado correctamente.");
	    respuesta.put("cantidadRegistros", resultados.size());

	    List<Map<String, Object>> detalle = new ArrayList<>();

	    for (Object[] fila : resultados) {
	        Map<String, Object> item = new HashMap<>();
	        item.put("id", fila[0]);
	        item.put("descripcion", fila[1]);
	        item.put("anio", fila[2]);
	        item.put("mes", fila[3]);
	        item.put("tabulados", fila[4]);
	        item.put("monto", fila[5]);
	        detalle.add(item);
	    }

	    respuesta.put("detalle", detalle);

	    return respuesta;
	}

	
	@GetMapping("/reporteDetallado/{anioDesde}/{mesDesde}/{anioHasta}/{mesHasta}")
	public Map<String, Object> generarReporteDetalladoLiquidaciones(
	        @PathVariable("anioDesde") int anioDesde,
	        @PathVariable("mesDesde") int mesDesde,
	        @PathVariable("anioHasta") int anioHasta,
	        @PathVariable("mesHasta") int mesHasta) {

	    List<Object[]> resultados = liquidacionService.generarReporteDetalladoLiquidaciones(
	            anioDesde, mesDesde, anioHasta, mesHasta);

	    Map<String, Object> respuesta = new HashMap<>();

	    if (resultados.isEmpty()) {
	        respuesta.put("mensaje", "No se encontraron liquidaciones en el rango.");
	        respuesta.put("cantidadRegistros", 0);
	        respuesta.put("detalle", Collections.emptyList());
	        return respuesta;
	    }

	    respuesta.put("mensaje", "Reporte generado correctamente.");
	    respuesta.put("cantidadRegistros", resultados.size());

	    List<Map<String, Object>> detalle = new ArrayList<>();

	    for (Object[] fila : resultados) {
	        Map<String, Object> item = new HashMap<>();
	        item.put("descripcion", fila[0]);
	        item.put("anio", fila[1]);
	        item.put("mes", fila[2]);
	        item.put("HABER_MENSUAL", fila[3]);
	        item.put("TITULO", fila[4]);
	        item.put("ANTIGUEDAD", fila[5]);
	        item.put("AJ", fila[6]);
	        detalle.add(item);
	    }

	    respuesta.put("detalle", detalle);

	    return respuesta;
	}


}
