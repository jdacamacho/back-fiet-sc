package com.unicauca.cfiet.solicitudes.domain.modelos;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
public class UsuarioLiviano {
    private String uuidUsuario;
    private String nombres;
    private String apellidos;
    private Boolean estado;

    public UsuarioLiviano(){}
}
