package com.HaberesMonolitico.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.HaberesMonolitico.entities.NOU;
import com.HaberesMonolitico.entities.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>{
	
	@Query("SELECT n FROM NOU n JOIN FETCH n.persona WHERE n.numeroNOU = :numeroNou")
	Optional<NOU> findByNou(@Param("numeroNou") int numeroNou);
}
