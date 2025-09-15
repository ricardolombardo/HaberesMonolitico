package com.HaberesMonolitico.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.HaberesMonolitico.entities.Usuario;
import com.HaberesMonolitico.services.UsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
    private UsuarioService usuarioService;

    @GetMapping("/getAll")
    public List<Usuario> obtenerTodos() {
        return usuarioService.listarUsuarios();
    }
	
    @GetMapping("/{id}")
    public Optional<Usuario> obtenerPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
    	usuarioService.eliminarUsuario(id);
    }
    
    /*INICIO DE METODOS PROPIOS*/

    @GetMapping("/buscarUsuarioNombre/{nombre}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String nombre) {
        return usuarioService.findByNombre(nombre)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }//http://localhost:8081/usuarios/buscarUsuarioNombre/Ricardo

    /*NO SEGURO*/
    @GetMapping("/validarUsuario")
    public ResponseEntity<String> validarUsuario(
            @RequestParam String nombre, 
            @RequestParam String password) {
        
        boolean esValido = usuarioService.validarUsuario(nombre, password);
        
        return esValido ? ResponseEntity.ok("Login exitoso")
                        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
    }
    //http://localhost:8081/usuarios/validarUsuario?nombre=Ricardo&password=12345678
    
    
    @PostMapping("/validarUsuarioPass")
    public ResponseEntity<String> validarUsuario(@RequestBody Usuario request) {
        boolean esValido = usuarioService.validarUsuario(request.getNombre(), request.getPass());

        return esValido ? ResponseEntity.ok("Login exitoso")
                        : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos");
    }
    
    @GetMapping("/testUsuarios")
	public String testUsuarios() {
		return "El endpoint de usuario contesta";
	}

}
