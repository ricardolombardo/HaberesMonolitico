package com.HaberesMonolitico.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "CONCEPTO")
public class Concepto {
	
	@Id
	@Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="SALDO")
	private String saldo;
	
	@Column(name="CODIGO")
	private String codigo;
	
	@Column(name="REMUNERATIVO")
	private Boolean remunerativo;
	
	/*
    @OneToMany(mappedBy = "concepto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TabuladoConcepto> tabulados = new ArrayList<>();
    */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSaldo() {
		return saldo;
	}

	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getRemunerativo() {
		return remunerativo;
	}

	public void setRemunerativo(Boolean remunerativo) {
		this.remunerativo = remunerativo;
	}
	
	/*
	public List<TabuladoConcepto> getTabulados() {
		return tabulados;
	}

	public void setTabulados(List<TabuladoConcepto> tabulados) {
		this.tabulados = tabulados;
	}
	*/
	
	
}
