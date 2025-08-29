package com.HaberesMonolitico.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HaberesMonolitico.DTO.PersonaDTO;
import com.HaberesMonolitico.entities.Persona;
import com.HaberesMonolitico.services.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
    private PersonaService personaService;

    @GetMapping("/getAll")
    public List<Persona> obtenerTodos() {
        return personaService.listarPersonas();
    }

    @GetMapping("/{id}")
    public Optional<Persona> obtenerPorId(@PathVariable Long id) {
        return personaService.obtenerPersonaPorId(id);
    }
    
    @GetMapping("/nou/{numeroNou}")
    public ResponseEntity<Persona> obtenerPorNou(@PathVariable("numeroNou") int numeroNou) {
        Optional<Persona> personaOpt = personaService.obtenerPersonaPorNou(numeroNou);
        if (personaOpt.isPresent()) {
            Persona persona = personaOpt.get();
            return ResponseEntity.ok(persona);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
    

    @PostMapping
    public Persona crearPersona(@RequestBody Persona persona) {
        return personaService.guardarPersona(persona);
    }
    
    @PostMapping("/{crearPersonaConDTO}")
    public Persona crearPersonaConDTO(@RequestBody PersonaDTO persona) {
        return personaService.guardarPersonaConDTO(persona);
    }

    @DeleteMapping("/{id}")
    public void eliminarPersona(@PathVariable Long id) {
    	personaService.eliminarPersona(id);
    }
    
    @PutMapping("/{id}")
    public Persona actualizarPersona(@PathVariable("id") Long id, @RequestBody PersonaDTO dto) {
        return personaService.actualizarPersonaDesdeDTO(id, dto);
    }
    
    @GetMapping("/testPersonas")
	public String testPersonas() {
		return "El endpoint de persona contesta";
	}
	
}