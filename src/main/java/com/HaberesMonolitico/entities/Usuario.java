package com.HaberesMonolitico.entities;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
	@Column(name="USUARIO")
	private String usuario;
    
	@Column(name="NOMBRE")
	private String nombre;
    
	@Column(name="APELLIDO")
	private String apellido;
    
	@Column(name="PASS")
	private String pass;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
    
    
}

