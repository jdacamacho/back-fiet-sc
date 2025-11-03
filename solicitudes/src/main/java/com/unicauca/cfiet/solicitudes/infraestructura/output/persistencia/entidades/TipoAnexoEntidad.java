package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "tiposAnexos")
@Getter
@Setter
@NoArgsConstructor
public class TipoAnexoEntidad {
    @Id
    private String uuidTipoAnexo;
    @Column(nullable = false, length = 45)
    private String nombre;
    @Column(length = 200)
    private String descripcion;
    @Column(nullable = false, length = 45)
    private String formato;
    @Column(nullable = false)
    private Boolean obligatoriedad;
    @ManyToOne
    @JoinColumn(name = "uuidTipoSolicitud", nullable = false)
    private TipoSolicitudEntidad objTipoSolicitud;
}
