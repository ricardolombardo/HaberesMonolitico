package com.HaberesMonolitico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.HaberesMonolitico.entities.Evento;
import com.HaberesMonolitico.repositories.EventoRepository;

public class EventoService {

	@Autowired
	EventoRepository eventoRepository;
	
	public List<Evento> listarEventos(){
		return eventoRepository.findAll();
	}
	
	public Optional<Evento> obtenerEventoPorId(Long id){
		return eventoRepository.findById(id);
	}
	
	public Evento guardarJerarquia(Evento evento) {
		return eventoRepository.save(evento);
	}
	
	public void eliminarJerarquia(Long id) {
		eventoRepository.deleteById(id);
	}
}
