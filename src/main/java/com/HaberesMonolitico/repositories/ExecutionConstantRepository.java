package com.HaberesMonolitico.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.HaberesMonolitico.entities.ExecutionConstant;

public interface ExecutionConstantRepository extends JpaRepository<ExecutionConstant, Long> {
	Optional<ExecutionConstant> findByKey(String key);
}

