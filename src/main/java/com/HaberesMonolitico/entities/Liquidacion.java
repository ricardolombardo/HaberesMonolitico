package com.HaberesMonolitico.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "LIQUIDACION")
public class Liquidacion {
	
	@Id
	@Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="ANIO")
	private int anio;
	
	@Column(name="MES")
	private int mes;
	
	@Column(name="LIQUIDADA")
	private Boolean liquidada;
	
	@OneToMany(mappedBy = "liquidacion", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	/*
	 * Hibernate/JPA va a usar la columna ID_LIQUIDACION en la tabla TABULADO como foreign key.
	 * @JsonManagedReference hace que al serializar Liquidacion, incluya la lista de Tabulado, pero que al serializar Tabulado, 
	 * no vuelva a serializar Liquidacion.
	 */
	private List<Tabulado> tabulados;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public List<Tabulado> getTabulados() {
		return tabulados;
	}

	public void setTabulados(List<Tabulado> tabulados) {
		this.tabulados = tabulados;
	}

	public Boolean getLiquidada() {
		return liquidada;
	}

	public void setLiquidada(Boolean liquidada) {
		this.liquidada = liquidada;
	}
	
	
	
}
