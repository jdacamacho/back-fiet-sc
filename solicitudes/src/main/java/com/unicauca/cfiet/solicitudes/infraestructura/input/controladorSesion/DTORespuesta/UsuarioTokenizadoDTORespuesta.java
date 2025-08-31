package com.unicauca.cfiet.solicitudes.infraestructura.input.controladorSesion.DTORespuesta;

import com.unicauca.cfiet.solicitudes.infraestructura.input.controladorUsuarios.DTORespuesta.UsuarioDTORespuesta;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Julian David Camacho Erazo  {@literal <jdacamacho@unicauca.edu.co>}
 */
@Getter
@Setter
public class UsuarioTokenizadoDTORespuesta extends UsuarioDTORespuesta{
    private String token;

    public UsuarioTokenizadoDTORespuesta(){
        super();
    }
}
