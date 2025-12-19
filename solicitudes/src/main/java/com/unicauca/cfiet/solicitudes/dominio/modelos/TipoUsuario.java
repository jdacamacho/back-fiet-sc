package com.unicauca.cfiet.solicitudes.dominio.modelos;

import lombok.*;

import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoUsuario {
    private String uuidTipoUsuario;
    private String nombre;
    private List<Usuario> usuarios;
}
