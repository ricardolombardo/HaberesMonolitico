package com.HaberesMonolitico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HaberesMonolitico.entities.EventoNOU;
import com.HaberesMonolitico.repositories.EventoNOURepository;

@Service
public class EventoNOUService {

    @Autowired
    private final EventoNOURepository eventoNouRepository;

    public EventoNOUService(EventoNOURepository eventoNouRepository) {
        this.eventoNouRepository = eventoNouRepository;
    }

    // Crear o actualizar
    public EventoNOU guardar(EventoNOU eventoNou) {
        return eventoNouRepository.save(eventoNou);
    }

    // Obtener todos
    public List<EventoNOU> listarTodos() {
        return eventoNouRepository.findAll();
    }

    // Buscar por ID
    public Optional<EventoNOU> buscarPorId(Long id) {
        return eventoNouRepository.findById(id);
    }

    // Eliminar por ID
    public void eliminarPorId(Long id) {
        eventoNouRepository.deleteById(id);
    }
    
    public List<EventoNOU> buscarPorNouId(Long nouId) {
        return eventoNouRepository.findByNouId(nouId);
    }
}
