package com.HaberesMonolitico.entities;


import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "PERSONA")
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(name="NOMBRE")
	String nombre;
	
	@Column(name="APELLIDO_PATERNO")
	String apellidoPaterno;
	
	@Column(name="APELLIDO_MATERNO")
	String apellidoMaterno;
	
	@ManyToOne
	@JoinColumn(name = "ID_DEPARTAMENTO")
	private Departamento departamento;

	@ManyToOne
	@JoinColumn(name="ID_JERARQUIA")
	private Jerarquia jerarquia;
	
	@ManyToOne
	@JoinColumn(name="ID_TITULO")
	private Titulo titulo;
	
	@Column(name="ANTIGUEDAD")
	private int antiguedad;
	
	@Column(name="ACTIVO")
	private Boolean activo;
	
	public Long getId() {
		return id;
	}

	public void setIdPersona(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Jerarquia getJerarquia() {
		return jerarquia;
	}

	public void setJerarquia(Jerarquia jerarquia) {
		this.jerarquia = jerarquia;
	}

	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
