package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolDTORespuesta {
    private String uuid;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}
