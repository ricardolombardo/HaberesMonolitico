package com.HaberesMonolitico.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONTRIBUCIONES")  
public class TareaGeneracionContribuciones extends Tarea{

}
