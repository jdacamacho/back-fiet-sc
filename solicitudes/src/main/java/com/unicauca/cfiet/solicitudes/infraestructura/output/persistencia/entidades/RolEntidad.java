package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class RolEntidad {
    @Id
    private String uuid;
    @Column(nullable = false, unique = true, length = 45)
    private String nombre;
    @Column(nullable = true, unique = false, length = 300)
    private String descripcion;
    @Column(nullable = false)
    private Boolean estado;
}
