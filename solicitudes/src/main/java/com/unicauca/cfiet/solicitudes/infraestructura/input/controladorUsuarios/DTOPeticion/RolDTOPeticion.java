package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTOPeticion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDTOPeticion {
    private String descripcion;
    private Boolean estado;
}
