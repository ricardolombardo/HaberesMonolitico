package com.HaberesMonolitico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.HaberesMonolitico.entities.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{

}
