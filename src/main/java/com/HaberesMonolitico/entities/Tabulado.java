package com.HaberesMonolitico.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TABULADO")
public class Tabulado {
	
	@Id
	@Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@ManyToOne
	@JoinColumn(name = "ID_NOU")
	private NOU nou;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LIQUIDACION")
	@JsonBackReference("liquidacion-tabulados")  //nombre único
	private Liquidacion liquidacion;
	
    @OneToMany(mappedBy = "tabulado", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("tabulado-conceptos")  //otro nombre único
    private List<TabuladoConcepto> tabuladoConceptos = new ArrayList<>();
	

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

	public NOU getNou() {
		return nou;
	}

	public void setNou(NOU nou) {
		this.nou = nou;
	}

	public Liquidacion getLiquidacion() {
		return liquidacion;
	}

	public void setLiquidacion(Liquidacion liquidacion) {
		this.liquidacion = liquidacion;
	}

	public List<TabuladoConcepto> getTabuladoConceptos() {
		return tabuladoConceptos;
	}

	public void setTabuladoConceptos(List<TabuladoConcepto> tabuladoConceptos) {
		this.tabuladoConceptos = tabuladoConceptos;
	}

}
