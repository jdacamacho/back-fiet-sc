package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSolicitudes.DTOPeticion;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
public class SolicitudActualizarDTOPeticion {
    private String consecutivo;
    private String nombre;
    private String descripcion;
    private String estado;
    private String uuidFuncionario;
    private String uuidOrdenDelDia;
}
