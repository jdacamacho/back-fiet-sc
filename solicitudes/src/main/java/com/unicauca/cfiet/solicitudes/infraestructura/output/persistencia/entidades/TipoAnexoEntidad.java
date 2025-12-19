package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "tiposAnexos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoAnexoEntidad {
    @Id
    private String uuidTipoAnexo;
    @Column(nullable = false, length = 200)
    private String nombre;
    @Column(length = 200)
    private String descripcion;
    @Column(nullable = false, length = 200)
    private String formato;
    @Column(nullable = false)
    private Boolean obligatoriedad;
    @ManyToOne
    @JoinColumn(name = "uuidTipoSolicitud", nullable = false)
    private TipoSolicitudEntidad objTipoSolicitud;
}
