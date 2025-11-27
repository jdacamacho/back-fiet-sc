package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "tiposSolicitudes")
@Getter
@Setter
public class TipoSolicitudEntidad {
    @Id
    private String uuidTipoSolicitud;
    @Column(nullable = false, length = 45)
    private String nombre;
    @Column(length = 200)
    private String descripcion;
    @Column(nullable = false, length = 500)
    private String seccion;
    @Column(nullable = false, length = 500)
    private String perfilSolicitante;
    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "objTipoSolicitud",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TipoAnexoEntidad> anexos;
    @ManyToOne
    @JoinColumn(name = "uuidUsuario")
    private FuncionarioEntidad objFuncionarioEncargado;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "objTipoSolicitud"
    )
    private List<SolicitudEntidad> solicitudes;

    public TipoSolicitudEntidad(){
        this.anexos = new ArrayList<>();
    }
}
