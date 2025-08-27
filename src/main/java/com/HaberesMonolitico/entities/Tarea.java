package com.HaberesMonolitico.entities;

import java.util.Hashtable;

import jakarta.persistence.*;

@Entity
@Table(name = "TAREAS")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // una sola tabla para todas
@DiscriminatorColumn(name = "TIPO", discriminatorType = DiscriminatorType.STRING) 
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NOMBRE", nullable = false, length = 255)
    private String nombre;

    @Column(name = "DESCRIPCION", columnDefinition = "NVARCHAR(MAX)")
    private String descripcion;

    @Column(name = "TIPO", insertable = false, updatable = false)
    private String tipo;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public String getSP() {
    	return null;
    }
    
    public Hashtable<String, Object> getParametros(Long idLiquidacion){
    	return null;
    }
}
