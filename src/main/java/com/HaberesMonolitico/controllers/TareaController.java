package com.HaberesMonolitico.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.HaberesMonolitico.entities.Tarea;
import com.HaberesMonolitico.services.TareaService;

@RestController
@RequestMapping("/tareas")
public class TareaController {
	
	@Autowired
	TareaService tareaService;
	
	@GetMapping("/getAll")
	public List<Tarea> getAll(){
		return tareaService.listarTareas();
	}

}
