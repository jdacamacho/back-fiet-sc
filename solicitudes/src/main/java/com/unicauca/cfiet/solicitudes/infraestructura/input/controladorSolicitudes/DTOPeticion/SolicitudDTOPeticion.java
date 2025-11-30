package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
public class SolicitudDTOPeticion {
    private String consecutivo;
    private String nombre;
    private String descripcion;
    private String uuidTipoSolicitud;
    private List<AnexoDTOPeticion> anexos;
    private String uuidOrdenDelDia;
    private InformacionSolicitanteDTOPeticion informacionSolicitante;
}
