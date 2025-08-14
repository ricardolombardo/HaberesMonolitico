package com.HaberesMonolitico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.HaberesMonolitico.entities.Concepto;

@Repository
public interface ConceptoRepository extends JpaRepository<Concepto, Long>{

}
