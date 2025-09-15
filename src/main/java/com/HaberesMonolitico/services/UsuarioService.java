package com.HaberesMonolitico.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HaberesMonolitico.entities.Usuario;
import com.HaberesMonolitico.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> findByNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
    	usuarioRepository.deleteById(id);
    }
    
    public boolean validarUsuario(String nombre,String psw){
    	Optional<Usuario> usuario=this.findByNombre(nombre);
    	if(usuario.get().getPass().equals(psw)) {
    		return true;
    	}else {
    		return false;	
    	}
    	
    }
}
