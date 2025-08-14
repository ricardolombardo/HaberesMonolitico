package com.HaberesMonolitico.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.HaberesMonolitico.entities.Tabulado;

public interface TabuladoRepository extends JpaRepository<Tabulado, Long>{
	List<Tabulado> findByLiquidacionId(Long idLiquidacion);
}
