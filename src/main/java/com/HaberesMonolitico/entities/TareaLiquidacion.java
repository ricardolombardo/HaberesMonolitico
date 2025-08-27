package com.HaberesMonolitico.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(
    name = "TAREA_LIQUIDACION",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_TAREA", "ID_LIQUIDACION"})
    }
)
public class TareaLiquidacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_TAREA", nullable = false)
    @JsonBackReference("tarea-liquidaciones")
    private Tarea tarea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_LIQUIDACION", nullable = false)
    @JsonBackReference("liquidacion-tareas")
    private Liquidacion liquidacion;

    @Column(name = "ORDEN", nullable = false)
    private Integer orden;

    @Column(name = "ESTADO_EJECUCION", length = 50, nullable = false)
    private String estadoEjecucion = "PENDIENTE";

    public TareaLiquidacion() {
    }

    public TareaLiquidacion(Tarea tarea, Liquidacion liquidacion, Integer orden, String estadoEjecucion) {
        this.tarea = tarea;
        this.liquidacion = liquidacion;
        this.orden = orden;
        this.estadoEjecucion = estadoEjecucion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public Liquidacion getLiquidacion() {
        return liquidacion;
    }

    public void setLiquidacion(Liquidacion liquidacion) {
        this.liquidacion = liquidacion;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    public String getEstadoEjecucion() {
        return estadoEjecucion;
    }

    public void setEstadoEjecucion(String estadoEjecucion) {
        this.estadoEjecucion = estadoEjecucion;
    }
}


