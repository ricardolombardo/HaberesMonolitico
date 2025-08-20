package com.HaberesMonolitico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HaberesMonolitico.entities.Titulo;
import com.HaberesMonolitico.repositories.TituloRepository;

@Service
public class TituloService {
	
	@Autowired
    private TituloRepository tituloRepository;

	public List<Titulo> listarTitulos(){
		return tituloRepository.findAll();
	}
	
	public Optional<Titulo> obtenerTituloPorId(Long id){
		return tituloRepository.findById(id);
	}
	
	public Titulo guardarJerarquia(Titulo titulo) {
		return tituloRepository.save(titulo);
	}
	
	public void eliminarTitulo(Long id) {
		tituloRepository.deleteById(id);
	}

}
