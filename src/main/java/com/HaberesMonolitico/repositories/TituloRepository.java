package com.HaberesMonolitico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HaberesMonolitico.entities.Titulo;

@Repository
public interface TituloRepository extends JpaRepository<Titulo, Long>{

}
