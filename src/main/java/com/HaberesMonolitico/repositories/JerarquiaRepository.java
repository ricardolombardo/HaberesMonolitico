package com.HaberesMonolitico.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.HaberesMonolitico.entities.Jerarquia;

@Repository
public interface JerarquiaRepository extends JpaRepository<Jerarquia, Long>{

}
