package com.HaberesMonolitico.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HaberesMonolitico.entities.Evento;
import com.HaberesMonolitico.entities.EventoNOU;
import com.HaberesMonolitico.entities.NOU;
import com.HaberesMonolitico.services.NOUService;

@RestController
@RequestMapping("/nous")
public class NOUController {
	
	@Autowired
	NOUService nouService;
	
	 @GetMapping("/getAll")
	 public List<NOU> getAll(){
		 return nouService.listarNOUs();
	 }
	 
	 @GetMapping("/{id}")
	 public Optional<NOU> obtenerPorId(@PathVariable("id") Long id) {
		 return nouService.obtenerNouPorId(id);
	 }
	 
	 @GetMapping("/{id}/eventosNou")
	 public ResponseEntity<List<EventoNOU>> obtenerEventosNouPorNou(@PathVariable("id") Long idNou) {
		 List<EventoNOU> eventosNou = nouService.obtenerEventosNouPorNou(idNou);
		 if (eventosNou.isEmpty()) {
			 return ResponseEntity.noContent().build();
			 }
		 return ResponseEntity.ok(eventosNou);
	 }
	 
	 @GetMapping("/persona/{personaId}")
	 public List<NOU> obtenerNOUsPorPersona(@PathVariable("personaId") Long personaId) {
		 return nouService.obtenerNOUPorPersonaId(personaId);
	 }
	 
	@PostMapping
	public NOU crearEvento(@RequestBody NOU nou) {
		return nouService.guardarNou(nou);
	}

}
