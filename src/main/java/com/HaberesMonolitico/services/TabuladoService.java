package com.HaberesMonolitico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HaberesMonolitico.entities.Tabulado;
import com.HaberesMonolitico.repositories.TabuladoRepository;

@Service
public class TabuladoService {

	@Autowired
	private TabuladoRepository tabuladoRepository;
	
	public List<Tabulado> listarTabulados(){
		return tabuladoRepository.findAll();
	}
	
	public Optional<Tabulado> obtenerTabuladoPorId(Long id) {
		return tabuladoRepository.findById(id);
	}
	
	public Tabulado guardarTabulado(Tabulado tabulado) {
		return tabuladoRepository.save(tabulado);
	}
	
	public void eliminarTabulado(Long id) {
		tabuladoRepository.deleteById(id);
	}
	
    public List<Tabulado> findByLiquidacionId(Long idLiquidacion) {
        return tabuladoRepository.findByLiquidacionId(idLiquidacion);
    }
	
}
