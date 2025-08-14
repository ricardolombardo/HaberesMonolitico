package com.HaberesMonolitico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.HaberesMonolitico.entities.Liquidacion;

@Repository
public interface LiquidacionRepository extends JpaRepository<Liquidacion,Long>{
	List<Liquidacion> findByAnioAndMes(int anio, int mes);
	
	
	@Query("""
		    SELECT l
		    FROM Liquidacion l
		    WHERE (l.anio > :anioDesde OR (l.anio = :anioDesde AND l.mes >= :mesDesde))
		      AND (l.anio < :anioHasta OR (l.anio = :anioHasta AND l.mes <= :mesHasta))
		""")
		List<Liquidacion> findByRangoAnioMes(
		    @Param("anioDesde") int anioDesde,
		    @Param("mesDesde") int mesDesde,
		    @Param("anioHasta") int anioHasta,
		    @Param("mesHasta") int mesHasta
		);


}
