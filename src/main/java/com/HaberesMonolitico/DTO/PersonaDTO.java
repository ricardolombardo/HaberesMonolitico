package com.HaberesMonolitico.DTO;

public class PersonaDTO {
	private Long id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Long departamentoId;
    private Long jerarquiaId;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public Long getDepartamentoId() { return departamentoId; }
    public void setDepartamentoId(Long departamentoId) { this.departamentoId = departamentoId; }
	
    public Long getJerarquiaId() {
		return jerarquiaId;
	}
	public void setJerarquiaId(Long jerarquiaId) {
		this.jerarquiaId = jerarquiaId;
	}
    
    
}
