package com.HaberesMonolitico.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.HaberesMonolitico.services.TabuladoService;
import com.HaberesMonolitico.entities.Tabulado;

@RestController
@RequestMapping("/tabulados")
public class TabuladoController {

	@Autowired
	TabuladoService tabuladoService;
	
	 @GetMapping("/getAll")
	 public List<Tabulado> getAll(){
		 return tabuladoService.listarTabulados();
	 }
	 
	 @GetMapping("/{id}")
	 public Optional<Tabulado> obtenerPorId(@PathVariable("id") Long id) {
		 return tabuladoService.obtenerTabuladoPorId(id);
	 }
	 
	 
	 @GetMapping("/porLiquidacion")
	 public List<Tabulado> getTabuladosDeUnaLiquidacion(@RequestParam("idLiquidacion") Long idLiquidacion) {
	     return tabuladoService.findByLiquidacionId(idLiquidacion);
	 }

	
}
