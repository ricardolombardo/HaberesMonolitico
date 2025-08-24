package com.HaberesMonolitico.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "TABULADO_CONCEPTO")
public class TabuladoConcepto {

	@Id
	@Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TABULADO", nullable = false)
	@JsonBackReference("tabulado-conceptos")
    private Tabulado tabulado;

    @ManyToOne
    @JoinColumn(name = "ID_CONCEPTO", nullable = false)
    //@JsonBackReference
    private Concepto concepto;

    @Column(length = 50)
    private String sentido;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal monto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tabulado getTabulado() {
		return tabulado;
	}

	public void setTabulado(Tabulado tabulado) {
		this.tabulado = tabulado;
	}

	public Concepto getConcepto() {
		return concepto;
	}

	public void setConcepto(Concepto concepto) {
		this.concepto = concepto;
	}

	public String getSentido() {
		return sentido;
	}

	public void setSentido(String sentido) {
		this.sentido = sentido;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
    
    
	
}
