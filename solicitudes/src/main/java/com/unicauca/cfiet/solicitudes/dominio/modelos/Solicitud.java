package com.unicauca.cfiet.solicitudes.dominio.modelos;

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
public class Solicitud {
    private String uuidSolicitud;
    private String consecutivo;
    private String nombre;
    private String descripcion;
    private String estado;
    private TipoSolicitud tipoSolicitud;
    private List<Anexo> anexos;
    private OrdenDelDia ordenDelDia;
    private InformacionSolicitante informacionSolicitante;
}
