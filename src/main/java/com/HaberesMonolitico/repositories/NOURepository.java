package com.HaberesMonolitico.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.HaberesMonolitico.entities.EventoNOU;
import com.HaberesMonolitico.entities.NOU;

@Repository
public interface NOURepository extends JpaRepository<NOU, Long>{
	
	@Query("SELECT e FROM EventoNOU e JOIN FETCH e.evento WHERE e.nou.id = :idNou")
	List<EventoNOU> findByNouId(@Param("idNou") Long idNou);
	
	List<NOU> findByPersonaId(Long personaId);
	
	
}
