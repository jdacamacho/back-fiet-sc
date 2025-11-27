package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "solicitudes")
@Getter
@Setter
@NoArgsConstructor
public class SolicitudEntidad {
    @Id
    @Column(length = 100)
    private String uuidSolicitud;
    @Column(nullable = false, unique = true, length = 100)
    private String consecutivo;
    @Column(nullable = false, length = 100)
    private String nombre;
    @Column(length = 100)
    private String descripcion;
    @Column(nullable = false, length = 100)
    private String estado;
    @ManyToOne
    @JoinColumn(name = "uuidTipoSolicitud", nullable = false)
    private TipoSolicitudEntidad objTipoSolicitud;
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "objSolicitud"
    )
    private List<AnexoEntidad> anexos;
    @ManyToOne
    @JoinColumn(name = "uuidOrdenDelDia", nullable = false)
    private OrdenDelDiaEntidad objOrdenDelDia;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "uuidInformacionSolicitante", referencedColumnName = "uuidInformacionSolicitante")
    private InformacionSolicitanteEntidad informacionSolicitante;
}
