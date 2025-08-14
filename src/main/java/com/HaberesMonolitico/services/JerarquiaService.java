package com.HaberesMonolitico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HaberesMonolitico.entities.Jerarquia;
import com.HaberesMonolitico.repositories.JerarquiaRepository;

@Service
public class JerarquiaService {
	
	@Autowired
    private JerarquiaRepository jerarquiaRepository;

	public List<Jerarquia> listarJerarquias(){
		return jerarquiaRepository.findAll();
	}
	
	public Optional<Jerarquia> obtenerJerarquiaPorId(Long id){
		return jerarquiaRepository.findById(id);
	}
	
	public Jerarquia guardarJerarquia(Jerarquia jerarquia) {
		return jerarquiaRepository.save(jerarquia);
	}
	
	public void eliminarJerarquia(Long id) {
		jerarquiaRepository.deleteById(id);
	}
	
}
