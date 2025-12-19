package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "ordenesDelDia")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdenDelDiaEntidad {
    @Id
    @Column(length = 200)
    private String uuidOrdenDelDia;
    @Column(nullable = false, length = 200)
    private String nombre;
    @Column(length = 500)
    private String descripcion;
    @Column(length = 200)
    private String ciudad;
    @Column(length = 200)
    private String fecha;
    @Column(length = 200)
    private String horaInicio;
    @Column(length = 200)
    private String horaFin;
    @Column(length = 200)
    private String lugarReunion;
    @Column(nullable = false, length = 200)
    private String numeroActa;
    @Column(nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;
    @Column(nullable = false)
    private boolean estado;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
