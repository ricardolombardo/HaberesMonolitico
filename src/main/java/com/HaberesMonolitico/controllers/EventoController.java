package com.HaberesMonolitico.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HaberesMonolitico.entities.Evento;
import com.HaberesMonolitico.services.EventoService;

@RestController
@RequestMapping("/eventos")
public class EventoController {

	@Autowired
	EventoService eventoService;
	
	@GetMapping("/getAll")
	public List<Evento> obtenerTodos(){
		return eventoService.listarEventos();
	}
	
	@GetMapping("/{id}")
	public Optional<Evento> obtenerPorId(Long id){
		return eventoService.obtenerEventoPorId(id);
	}
	
	@PostMapping
	public Evento crearEvento(@RequestBody Evento evento) {
		return eventoService.guardarEvento(evento);
	}
	
}
