package com.unicauca.cfiet.solicitudes.domain.modelos;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Data
@NoArgsConstructor
public class TipoUsuario {
    private String uuidTipoUsuario;
    private String nombre;
    private List<Usuario> usuarios;
}
