package com.unicauca.cfiet.solicitudes.infraestructura.output.persistencia.entidades;

import jakarta.persistence.*;
import lombok.*;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Entity
@Table(name = "informacionSolicitantes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacionSolicitanteEntidad {
    @Id
    @Column(length = 100)
    private String uuidInformacionSolicitante;
    @Column(nullable = false, length = 100)
    private String tipoDocumento;
    @Column(nullable = false, length = 100)
    private String numeroDocumento;
    @Column(nullable = false, length = 100)
    private String nombres;
    @Column(nullable = false, length = 100)
    private String apellidos;
    @Column(nullable = false, length = 100)
    private String telefono;
    @Column(nullable = false, length = 100)
    private String correoElectronico;
    @OneToOne(mappedBy = "informacionSolicitante")
    private SolicitudEntidad solicitud;
}
