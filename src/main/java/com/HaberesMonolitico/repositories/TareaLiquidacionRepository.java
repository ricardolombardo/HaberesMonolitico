package com.HaberesMonolitico.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.HaberesMonolitico.entities.TareaLiquidacion;

@Repository
public interface TareaLiquidacionRepository extends JpaRepository<TareaLiquidacion, Long>{

	List<TareaLiquidacion> findByLiquidacionId(Long idLiquidacion);

}
