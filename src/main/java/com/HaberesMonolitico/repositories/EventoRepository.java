package com.HaberesMonolitico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.HaberesMonolitico.entities.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long>{

}
