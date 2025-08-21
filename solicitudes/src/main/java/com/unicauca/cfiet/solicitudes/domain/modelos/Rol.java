package com.unicauca.cfiet.solicitudes.domain.modelos;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Data
@NoArgsConstructor
public class Rol {
    private String uuid;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}
