package com.HaberesMonolitico.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HaberesMonolitico.DAO.LiquidacionDAO;
import com.HaberesMonolitico.entities.Liquidacion;
import com.HaberesMonolitico.repositories.LiquidacionRepository;

@Service
public class LiquidacionService {
	
	@Autowired
	LiquidacionRepository liquidacionRepository;
	
	@Autowired
	private LiquidacionDAO liquidacionDAO; 
	
	public List<Liquidacion> listarLiquidaciones(){
		return liquidacionRepository.findAll();
	}
	
	public List<Liquidacion> listarLiquidacionesPorAnioYMes(int anio, int mes) {
	    return liquidacionRepository.findByAnioAndMes(anio, mes);
	}
	
	public List<Liquidacion> listarLiquidacionesPorRango(int anioDesde, int mesDesde, int anioHasta, int mesHasta) {
	    return liquidacionRepository.findByRangoAnioMes(anioDesde, mesDesde, anioHasta, mesHasta);
	}

	
	public Optional<Liquidacion> obtenerLiquidacionPorId(Long id){
		return liquidacionRepository.findById(id);
	}
	
	public Liquidacion guardarLiquidacion(Liquidacion liquidacion) {
		return liquidacionRepository.save(liquidacion);
	}
	
    public void generarTabulados(Long idLiquidacion) {
    	liquidacionDAO.ejecutarSPGenerarTabulados(idLiquidacion);
    }
    
    public void generarBasicos(Long idLiquidacion) {
    	liquidacionDAO.ejecutarSPGenerarBasico(idLiquidacion);
    }

}
