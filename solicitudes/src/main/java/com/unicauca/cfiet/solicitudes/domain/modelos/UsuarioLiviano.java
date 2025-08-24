package com.unicauca.cfiet.solicitudes.domain.modelos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
@SuperBuilder
public class UsuarioLiviano {
    private String uuidUsuario;
    private String nombres;
    private String apellidos;
    private Boolean estado;

    public UsuarioLiviano(){}
}
