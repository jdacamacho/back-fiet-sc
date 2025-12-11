package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "anexos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnexoEntidad {
    @Id
    @Column(length = 100)
    private String uuidAnexo;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(nullable = false, length = 300)
    private String urlAnexo;
    @ManyToOne
    @JoinColumn(name = "uuidSolicitud", nullable = false)
    private SolicitudEntidad objSolicitud;
}
