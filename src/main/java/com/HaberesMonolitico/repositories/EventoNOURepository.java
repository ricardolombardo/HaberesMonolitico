package com.HaberesMonolitico.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.HaberesMonolitico.entities.EventoNOU;

@Repository
public interface EventoNOURepository extends JpaRepository<EventoNOU, Long>{
	List<EventoNOU> findByNouId(Long nouId);
}
