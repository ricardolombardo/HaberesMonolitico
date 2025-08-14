package com.HaberesMonolitico.entities;

import java.util.List;

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
@Table(name = "NOU")
public class NOU {

	@Id
	@Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_PERSONA")
	private Persona persona;
	
	@Column(name="NUMERO_NOU")
	private int numeroNOU;
	
	@OneToMany(mappedBy = "nou", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<EventoNOU> eventosNou;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setIdPersona(Persona persona) {
		this.persona = persona;
	}
	public int getNumeroNOU() {
		return numeroNOU;
	}
	public void setNumeroNOU(int numeroNOU) {
		this.numeroNOU = numeroNOU;
	}
	public List<EventoNOU> getEventosNou() {
		return eventosNou;
	}
	public void setEventosNou(List<EventoNOU> eventosNou) {
		this.eventosNou = eventosNou;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}
