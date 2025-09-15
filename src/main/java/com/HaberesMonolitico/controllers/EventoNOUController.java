package com.HaberesMonolitico.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HaberesMonolitico.entities.EventoNOU;
import com.HaberesMonolitico.services.EventoNOUService;


@RestController
@RequestMapping("/eventosNOUs")
public class EventoNOUController {
	@Autowired
	EventoNOUService eventoNOUService;
	
	@GetMapping("/getAll")
	public List<EventoNOU> obtenerTodos(){
		return eventoNOUService.listarTodos();
	}
	
	@GetMapping("/{id}")
	public Optional<EventoNOU> obtenerPorId(Long id){
		return eventoNOUService.buscarPorId(id);
	}
	
	@PostMapping
	public EventoNOU crearEventoNOU(@RequestBody EventoNOU eventoNOU) {
		return eventoNOUService.guardar(eventoNOU);
	}
}
