package com.HaberesMonolitico.DTO;

public class TareaLiquidacionUpdateDTO {
    private Long idTarea;
    private Long idLiquidacion;
    private Integer orden;
    private String estadoEjecucion;

    public Long getIdTarea() { return idTarea; }
    public void setIdTarea(Long idTarea) { this.idTarea = idTarea; }

    public Long getIdLiquidacion() { return idLiquidacion; }
    public void setIdLiquidacion(Long idLiquidacion) { this.idLiquidacion = idLiquidacion; }

    public Integer getOrden() { return orden; }
    public void setOrden(Integer orden) { this.orden = orden; }

    public String getEstadoEjecucion() { return estadoEjecucion; }
    public void setEstadoEjecucion(String estadoEjecucion) { this.estadoEjecucion = estadoEjecucion; }
}
