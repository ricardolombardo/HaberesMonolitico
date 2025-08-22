package com.HaberesMonolitico.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HaberesMonolitico.entities.Tarea;
import com.HaberesMonolitico.repositories.TareaRepository;

@Service
public class TareaService {
	
	@Autowired
	 private TareaRepository tareaRepository;
	 
	 public List<Tarea> listarTareas() {
		 return tareaRepository.findAll();
	 }

}
