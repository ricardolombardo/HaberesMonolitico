package com.HaberesMonolitico.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.HaberesMonolitico.DTO.DepartamentoDTO;
import com.HaberesMonolitico.entities.Departamento;
import com.HaberesMonolitico.repositories.DepartamentoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<Departamento> listarDepartamentos() {
        return departamentoRepository.findAll();
    }
    
    public Departamento obtenerPorId(Long id) {
        return departamentoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Departamento no encontrado con ID: " + id));
    }
    
    public Departamento actualizarDepartamentoDesdeDTO(Long id, DepartamentoDTO dto) {
        Optional<Departamento> departamentoOpt = departamentoRepository.findById(id);
        if (departamentoOpt.isPresent()) {
        	Departamento departamento=departamentoOpt.get();
        	departamento.setNombre(dto.getNombre());
        	departamento.setDescripcion(dto.getDescripcion());
        	
        	return departamentoRepository.save(departamento);
        }else {
            throw new RuntimeException("Departamento no encontrado con ID: " + id);
        }
    }

	public Departamento guardarDepartamento(Departamento departamento) {
		return departamentoRepository.save(departamento);
	}        
}

