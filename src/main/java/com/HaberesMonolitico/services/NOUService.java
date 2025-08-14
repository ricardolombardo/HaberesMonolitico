package com.HaberesMonolitico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HaberesMonolitico.entities.EventoNOU;
import com.HaberesMonolitico.entities.NOU;
import com.HaberesMonolitico.repositories.NOURepository;

@Service
public class NOUService {
	
	@Autowired
	NOURepository nouRepository;
	
	public List<NOU> listarNOUs(){
		return nouRepository.findAll();
	}
	
	public Optional<NOU> obtenerNouPorId(Long id){
		return nouRepository.findById(id);
	}
	
	public NOU guardarNou(NOU nou) {
		return nouRepository.save(nou);
	}
	
	public List<NOU> obtenerNOUPorPersonaId(Long id){
		return nouRepository.findByPersonaId(id);
	}
	
    public List<EventoNOU> obtenerEventosNouPorNou(Long idNou) {
        return nouRepository.findByNouId(idNou);
    }

}
