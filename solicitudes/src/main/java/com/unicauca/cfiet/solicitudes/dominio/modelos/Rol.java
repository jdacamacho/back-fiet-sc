package com.unicauca.cfiet.solicitudes.dominio.modelos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Data
@NoArgsConstructor
public class Rol {
    private String uuidRol;
    private String nombre;
    private String descripcion;
    private Boolean estado;
}
