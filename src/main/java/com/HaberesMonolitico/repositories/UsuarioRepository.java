package com.HaberesMonolitico.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.HaberesMonolitico.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByNombre(String nombre);
}
