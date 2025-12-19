package com.unicauca.cfiet.solicitudes.dominio.modelos;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UsuarioLiviano {
    private String uuidUsuario;
    private String nombres;
    private String apellidos;
    private Boolean estado;
}
